package com.demo.wms.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.wms.dao.CardTypeDAO;
import com.demo.wms.dao.UserDAO;
import com.demo.wms.dao.UserRoleDAO;
import com.demo.wms.dto.CardType;
import com.demo.wms.dto.User;
import com.demo.wms.dto.UserRole;
import com.demo.wms.model.RegistrationModel;
import com.demo.wms.util.CardTypeEnum;
import com.demo.wms.util.WMSConstants;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	UserRoleDAO userRoleDao;
	
	@Autowired
	CardTypeDAO cardTypeDao;
	
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	
	
	public User checkUserExist(String email){
		return userDao.findUserByEmail(email);
	}
	
	public User addUser(RegistrationModel registration){
		User user =new User();
		user.setEmail(registration.getEmail());
		user.setShippingAddress(registration.getAddress());
		user.setPhoneNum(registration.getPhoneNumber());
		user.setActive(true);
		user.setUserRole(userRoleDao.findByRoleDesc(WMSConstants.ROLE_USER));
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		userDao.save(user);
		return user;
	}
	
	public List<User> getAllUsers(){
		return userDao.findAll();
	}
	
	public User findByUserId(String id){
		return userDao.findByUserId(Integer.parseInt(id));
	}
	
	@PostConstruct
	public void initialization(){
		List<UserRole> roles=(List<UserRole>) userRoleDao.findAll();
		if(roles.isEmpty()){
			UserRole userRole=new UserRole();
			userRole.setRoleDesc(WMSConstants.ROLE_ADMIN);
			userRoleDao.save(userRole);
			
			UserRole userRole1=new UserRole();
			userRole1.setRoleDesc(WMSConstants.ROLE_USER);
			userRoleDao.save(userRole1);
		}
		
		List<CardType> cardTypes=(List<CardType>) cardTypeDao.findAll();
		if(cardTypes.isEmpty()){
			CardTypeEnum[] cardTypeConstants=CardTypeEnum.VALUES;
			for(CardTypeEnum cardType:cardTypeConstants){
				CardType cardTypeObj=new CardType();
				cardTypeObj.setCardTypeDesc(cardType.desc);
				cardTypeDao.save(cardTypeObj);
			}
		}
		
		
		
	}
	

}
