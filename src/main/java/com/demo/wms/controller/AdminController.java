package com.demo.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.wms.service.CardService;
import com.demo.wms.service.UserService;
import com.demo.wms.service.WalletService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	CardService cardService;
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value ="/landingPage", method = RequestMethod.GET)
	public ModelAndView getLandingPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminLandingPage"); 
		return model;
	}
	
	@RequestMapping(value ="/getAllUsers", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {
		ModelAndView model = new ModelAndView();
		model.addObject("users", userService.getAllUsers());
		model.setViewName("listUsersPage"); 
		return model;
	}

	@RequestMapping(value ="/deactivate/{userId}" , method=RequestMethod.GET)
	public ModelAndView deactivateWalletUser(@PathVariable("userId")String userId){
		ModelAndView model = new ModelAndView();
		model.addObject("statusUpdated", walletService.changeStatusOfWallet(false, userId));
		model.setViewName("forward:/admin/getAllUsers"); 
		return model;
	}
	
	@RequestMapping(value ="/activate/{userId}" , method=RequestMethod.GET)
	public ModelAndView activateWalletUser(@PathVariable("userId")String userId){
		ModelAndView model = new ModelAndView();
		model.addObject("statusUpdated", walletService.changeStatusOfWallet(false, userId));
		model.setViewName("forward:/admin/getAllUsers"); 
		return model;
	}

	

}
