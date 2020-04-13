package com.example.demo.decorator;

import com.example.demo.model.CartItem;
import com.example.demo.model.Item;

public class CartItemDiscountCampaignDecorator extends CartItemCampaignDecorator{

	private double discount;
	 public CartItemDiscountCampaignDecorator(CartItem delegate, double discount) {
	        super(delegate);
	        this.discount = discount;
	    }
	    public double getPrice() {
	        return delegate.getPrice() - delegate.getPrice()*discount;
	    }
	 
	    public String getTitle() {
	        return "DISCOUNT "+(100*discount)+"% ";
	    }
}
