package com.demo.wms.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.demo.wms.dto.UserRole;

@Transactional
public interface UserRoleDAO extends CrudRepository<UserRole, Long> {

	UserRole findByRoleDesc(String roleDesc);
}
