package com.demo.wms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.wms.dto.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = null;
		try {
			 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			user = userService.checkUserExist(id);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		if(user==null){
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			String authStrings = user.getUserRole().getRoleDesc();
	        authorities.add(new SimpleGrantedAuthority(authStrings));

		System.out.println("authorities :"+authorities);
		return authorities;
	}
}
