package com.demo.wms.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class CardModel {
	
	@NotNull
	@NotBlank
	@Size(min=16, max=16,message="Card number must be of 16 characters")
	private String cardNumber;
	
	@NotNull
	@NotBlank
	@NumberFormat(style = Style.NUMBER)
	@Max(12)
	@Min(1)
	private String expiryMonth;
	
	@NotNull
	@NotBlank
	@NumberFormat(style = Style.NUMBER)
	@Min(2017)
	private String expiryYear;
	
	@NotNull
	@NotBlank
	private String nameOnCard;
	
	@NotNull
	@NotBlank
	@Size(min=3,max=4,message="CVV must be of 3 or 4 number")
	@NumberFormat(style = Style.NUMBER)
	private String cvv;
	
	@NotNull
	@NotBlank
	@Min(1)
	private String cardType;

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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
}
