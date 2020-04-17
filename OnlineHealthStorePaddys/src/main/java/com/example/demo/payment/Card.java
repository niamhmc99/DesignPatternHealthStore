package com.example.demo.payment;

public abstract class Card implements PaymentMethod{

	protected final String name;
	protected final String cardNumber;
	protected final String expires;

	
	public Card(String name, String cardNumber, String expires) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.expires = expires;
	}
	
	
	
	public String getName() {
		return name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getExpires() {
		return expires;
	}
	
	public abstract boolean checkDate(String expiry);

	public abstract boolean checkNumber(String cardNumber);

	
	
}
