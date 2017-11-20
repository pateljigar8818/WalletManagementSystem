package com.demo.wms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="CardType.findAll", query="SELECT c FROM CardType c")
public class CardType extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cardtype_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cardTypeId;
	
	@Column(name="cardTypeDesc")
	private String cardTypeDesc;
	
	public int getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getCardTypeDesc() {
		return cardTypeDesc;
	}

	public void setCardTypeDesc(String cardTypeDesc) {
		this.cardTypeDesc = cardTypeDesc;
	}

}
