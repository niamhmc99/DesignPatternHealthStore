package com.example.demo.decorator;

import com.example.demo.model.Item;

public class CartItemCampaignDecorator implements CartItemInterface {

	protected CartItemInterface delegate;
	
	public CartItemCampaignDecorator(CartItemInterface delegate){
        this.delegate = delegate;
    }
	
	@Override
	public int getCartItemId() {
		// TODO Auto-generated method stub
		return delegate.getCartItemId();
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return delegate.getPrice();
	}
	
	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return delegate.getQuantity();
	}
	
	public Item getItem() {
		return delegate.getItem();
	}

}
