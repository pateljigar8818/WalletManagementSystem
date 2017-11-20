package com.demo.wms.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ModelAndView welcomePage(HttpServletRequest request, Principal principal) {
		return login(request, principal);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("loginPage");
		return model;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView postRegistration(@Valid @ModelAttribute("registration")RegistrationModel registration, BindingResult errors) {
		ModelAndView model = new ModelAndView();
		if(errors.hasErrors()){
			model.setViewName("registrationPage");
		}else if(userService.checkUserExist(registration.getEmail())!=null){
			model.setViewName("loginPage");
			model.addObject("message", "User already exist");
		}else{
			userService.addUser(registration);
			model.setViewName("loginPage");
			model.addObject("message", "Registration Success");
		}
		return model;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView getRegistration() {
		ModelAndView model = new ModelAndView();
		model.setViewName("registrationPage");
		return model;
	}
	
	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public ModelAndView loginSuccess(HttpServletRequest request, Principal principal) {
		return login(request, principal);
	}
	
	public ModelAndView login(HttpServletRequest request, Principal principal){
		ModelAndView model = new ModelAndView();
		User user=null;
		if(principal!=null && StringUtils.isNotBlank(principal.getName()) && (user= userService.checkUserExist(principal.getName()))!=null){
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
