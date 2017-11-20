package com.demo.wms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.demo.wms.dto.Cards;
import com.demo.wms.dto.Wallet;

@Transactional
public interface CardDAO extends CrudRepository<Cards, Long> {
	
	List<Cards> findCardsByWallet(Wallet wallet);

}
