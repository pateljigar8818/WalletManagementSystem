package com.demo.wms.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.demo.wms.dto.User;
import com.demo.wms.dto.Wallet;

@Transactional
public interface WalletDAO extends CrudRepository<Wallet, Long> {
	
	Wallet findWalletByUser(User user);

}
