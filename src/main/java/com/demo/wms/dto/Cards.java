package com.demo.wms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="Cards.findAll", query="SELECT c FROM Cards c")
public class Cards extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cards_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cardsId;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiry_month")
	private String expiryMonth;
	
	@Column(name="expiry_year")
	private String expiryYear;
	
	@Column(name="name_on_card")
	private String nameOnCard;
	
	@Column(name="cvv")
	private String cvv;
	
	@OneToOne
	@JoinColumn(name="card_type_id")
	private CardType cardType;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="wallet_id")
	private Wallet wallet;

	public int getCardId() {
		return cardsId;
	}

	public void setCardId(int cardId) {
		this.cardsId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
}
