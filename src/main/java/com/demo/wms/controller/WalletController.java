package com.demo.wms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.wms.dto.User;
import com.demo.wms.model.CardModel;
import com.demo.wms.service.CardService;
import com.demo.wms.service.UserService;
import com.demo.wms.service.WalletService;

@Controller
public class WalletController {

	@Autowired
	CardService cardService;
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value ={"/wallet/createWallet","/user/addCard"}, method = RequestMethod.GET)
	public ModelAndView getCreateWallet(CardModel cardModel) {
		ModelAndView model = new ModelAndView();
		model.setViewName("cardPage"); 
		return model;
	}
	
	@RequestMapping(value ="/wallet/createWallet", method = RequestMethod.POST)
	public ModelAndView postCreateWallet(HttpServletRequest request,CardModel cardModel) {
		ModelAndView model = new ModelAndView();
		User user=null;
		if((user=userService.checkUserExist(request.getUserPrincipal().getName()))!=null){
			if(walletService.addCardToWallet(cardModel, user)){
				model.setViewName("redirect:/user/landingPage"); 
			}else{
				model.addObject("message", "Wallet cannot be created");
				model.setViewName("cardPage"); 
			}
		}else{
			model.addObject("message", "User not found");
			model.setViewName("cardPage"); 
		}
		return model;
	}

}
