package com.demo.wms.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.wms.dto.User;
import com.demo.wms.model.RegistrationModel;
import com.demo.wms.service.UserService;
import com.demo.wms.util.WMSConstants;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage"); 
		return model;
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("loginPage");
		return model;
	}
	
	@RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
	public ModelAndView postRegistration(RegistrationModel registration) {
		ModelAndView model = new ModelAndView();
		userService.addUser(registration);
		model.setViewName("loginPage");
		model.addObject("message", "Registration Success");
		return model;
	}
	
	@RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
	public ModelAndView getRegistration() {
		ModelAndView model = new ModelAndView();
		model.setViewName("registrationPage");
		return model;
	}
	
	@RequestMapping(value = {"/loginSuccess"}, method = RequestMethod.GET)
	public ModelAndView loginSuccess(HttpServletRequest request, Principal principal) {
		ModelAndView model = new ModelAndView();
		User user=null;
		if((user= userService.checkUserExist(principal.getName()))!=null){
			if(request.isUserInRole(WMSConstants.ROLE_USER)){
				if(user.getWallet()!=null && !user.getWallet().isDeleted() && user.getWallet().isActive() && user.getWallet().isWebInterfaceActive()){
					model.setViewName("forward:/user/landingPage");
				}else if(user.getWallet()==null){
					model.addObject("emptyWallet", true);
					model.setViewName("userPage");					
				}else{
					model.setViewName("userPage");
				}
			}else if(request.isUserInRole(WMSConstants.ROLE_ADMIN)){
				model.setViewName("forward:/admin/landingPage");
			}else{
				model.setViewName("welcomePage");
			}
		}else{
			model.setViewName("welcomePage");
		}
		return model;
	}
	
}
