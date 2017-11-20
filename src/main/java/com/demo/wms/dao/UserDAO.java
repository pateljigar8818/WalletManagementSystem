package com.demo.wms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.wms.dto.User;
import com.demo.wms.dto.UserRole;

@Transactional
public interface UserDAO extends PagingAndSortingRepository<User, Integer>, CrudRepository<User, Integer> {
	
	List<User> findAll();
	
	List<User> findUsersByUserRole(UserRole userRole);

	User findUserByPhoneNum(String phoneNumber);
	
	User findUserByEmail(String email);
	
	User findUserByPhoneNumAndUserRole(String phoneNumber, UserRole userRole);
	
	User findByUserId(int id);
}
