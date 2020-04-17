package com.example.demo.payment;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Visa extends Card{

	public Visa(String name, String cardNumber, String expires) {
		super(name,cardNumber,expires);
	}

	@Override
	public boolean pay(double amount) {

		if (checkNumber(this.cardNumber) && this.checkDate(this.expires)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkDate(String expiry) {
		ZoneId zoneId = ZoneId.of("GMT");
		LocalDate today = LocalDate.now(zoneId);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		LocalDate expiryDate = LocalDate.parse(expiry, formatter);
		if (expiryDate.isBefore(today)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean checkNumber(String cardNumber) {
		if (cardNumber.length() != 16) {
			return false;
		} else {
			return true;
		}
	}

}
