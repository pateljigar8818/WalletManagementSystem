package com.demo.wms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.wms.dao.CardDAO;
import com.demo.wms.dao.CardTypeDAO;
import com.demo.wms.dto.Cards;
import com.demo.wms.dto.Wallet;
import com.demo.wms.model.CardModel;
import com.demo.wms.util.CardTypeEnum;

@Service
public class CardService {
	
	@Autowired
	CardDAO cardDao;
	
	@Autowired
	CardTypeDAO cardTypeDao;
	
	public Cards createCard(CardModel cardModel){
		Cards card=new Cards();
		card.setCardNumber(cardModel.getCardNumber());
		card.setCvv(cardModel.getCvv());
		card.setNameOnCard(cardModel.getNameOnCard());
		card.setExpiryMonth(cardModel.getExpiryMonth());
		card.setExpiryYear(cardModel.getExpiryYear());
		card.setCardType(cardTypeDao.findByCardTypeDesc(CardTypeEnum.getNamebyID(Integer.parseInt(cardModel.getCardType()))));
		cardDao.save(card);
		return card;
	}
	
	public List<Cards> findCardsByWallet(Wallet wallet){
		return cardDao.findCardsByWallet(wallet);
	}
	
}
