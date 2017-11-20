package com.demo.wms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.wms.dao.CardTypeDAO;
import com.demo.wms.dao.WalletDAO;
import com.demo.wms.dto.Cards;
import com.demo.wms.dto.User;
import com.demo.wms.dto.Wallet;
import com.demo.wms.model.CardModel;

@Service
public class WalletService {
	
	@Autowired
	CardService cardService;
	
	@Autowired
	WalletDAO walletDao;
	
	@Autowired
	CardTypeDAO cardTypeDao;
	
	@Autowired
	UserService userService;
	
	public Wallet createWallet(Cards card, User user){
		Wallet wallet=null;
		if((wallet=walletDao.findWalletByUser(user))==null){
			wallet=new Wallet();
			wallet.addCards(card);
			wallet.setActive(true);
			wallet.setDeleted(false);
			wallet.setWebInterfaceActive(true);
			wallet.setUser(user);
			walletDao.save(wallet);
		}
		return wallet;
	}
	
	public boolean addCardToWallet(CardModel cardModel, User user){
		Wallet wallet=null;
		boolean isCardAddedToWallet=false;
		Cards card=null;
		if((card=cardService.createCard(cardModel))!=null && user!=null){
			if(user.getWallet()==null){
				wallet=createWallet(card, user);
			}else{
				wallet=user.getWallet();
				wallet.addCards(card);
				walletDao.save(wallet);
			}
			isCardAddedToWallet=true;
		}
		return isCardAddedToWallet;
	}
	
	public boolean changeStatusOfWallet(boolean isActive, String userId){
		boolean statusUpdated=false;
		if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
			User user=userService.findByUserId(userId);
			Wallet wallet=null;
			if(user!=null && (wallet=user.getWallet())!=null && wallet.isActive()!=isActive){
				wallet.setActive(isActive);
				walletDao.save(wallet);
				statusUpdated=true;
			}
		}
		return statusUpdated;
	}
	
	public boolean changeStatusOfUIInterface(boolean isActive, String userId){
		boolean statusUpdated=false;
		if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
			User user=userService.findByUserId(userId);
			Wallet wallet=null;
			if(user!=null && (wallet=user.getWallet())!=null && wallet.isWebInterfaceActive()!=isActive){
				wallet.setWebInterfaceActive(isActive);
				walletDao.save(wallet);
				statusUpdated=true;
			}
		}
		return statusUpdated;
	}
	
	public boolean deleteWallet(String userId){
		boolean statusUpdated=false;
		if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
			User user=userService.findByUserId(userId);
			Wallet wallet=null;
			if(user!=null && (wallet=user.getWallet())!=null && !wallet.isDeleted()){
				cardService.deleteCards(wallet.getCards());
				wallet.setDeleted(true);
				walletDao.save(wallet);
				statusUpdated=true;
			}
		}
		return statusUpdated;
	}
	
}
