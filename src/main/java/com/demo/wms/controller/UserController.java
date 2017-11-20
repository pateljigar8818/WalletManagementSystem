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
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	CardService cardService;
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value ="/landingPage", method = RequestMethod.GET)
	public ModelAndView landingPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("userLandingPage"); 
		return model;
	}
	
	@RequestMapping(value ="/displayWallet", method = RequestMethod.GET)
	public ModelAndView displayWallet(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		User user=userService.checkUserExist(request.getUserPrincipal().getName());
		if(user!=null && user.getWallet()!=null && !user.getWallet().isDeleted()){
			if(user.getWallet().isActive() && user.getWallet().isWebInterfaceActive()){
				model.addObject("cards", user.getWallet().getCards());
				model.setViewName("displayWalletPage"); 
			}else if(!user.getWallet().isActive()){
				model.addObject("message", "Your wallet is not active. Please contact administrator");
				model.setViewName("displayWalletPage"); 
			}else if(!user.getWallet().isWebInterfaceActive()){
				model.addObject("message", "Web interface is not active. Please contact administrator");
				model.setViewName("displayWalletPage"); 
			}
		}else{
			model.addObject("message", "User not found or wallet has been deleted.");
			model.setViewName("displayWalletPage"); 
		}
		return model;
	}
	
	@RequestMapping(value ="/addCard", method = RequestMethod.POST)
	public ModelAndView postAddCard(HttpServletRequest request,CardModel cardModel) {
		ModelAndView model = new ModelAndView();
		User user=null;
		if((user=userService.checkUserExist(request.getUserPrincipal().getName()))!=null){
			if(walletService.addCardToWallet(cardModel, user)){
				model.setViewName("redirect:/user/landingPage"); 
			}else{
				model.addObject("message", "Card cannot be added");
				model.setViewName("cardPage"); 
			}
		}else{
			model.addObject("message", "User not found");
			model.setViewName("cardPage"); 
		}
		return model;
	}
	
	

}
