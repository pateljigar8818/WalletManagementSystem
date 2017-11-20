package com.demo.wms.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.demo.wms.dto.CardType;

@Transactional
public interface CardTypeDAO extends CrudRepository<CardType, Long> {

	CardType findByCardTypeDesc(String cardDesc);
}
