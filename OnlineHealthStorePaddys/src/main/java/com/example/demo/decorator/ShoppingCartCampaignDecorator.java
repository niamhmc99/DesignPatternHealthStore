package com.example.demo.decorator;

import java.util.Collection;

import com.example.demo.model.CartItem;


public class ShoppingCartCampaignDecorator implements ShoppingCartInterface{

	protected ShoppingCartInterface delegate;
	public ShoppingCartCampaignDecorator(ShoppingCartInterface delegate) {
        this.delegate = delegate;
	}

	@Override
	public Double getTotalCost() {
        return delegate.getTotalCost();
	}

	@Override
	public Collection<CartItemInterface> getShoppingListItems() {
		// TODO Auto-generated method stub
        return delegate.getShoppingListItems();
	}

	@Override
	public void addCartItem(CartItem cartItem) {
		delegate.addCartItem(cartItem);	
	}

	@Override
	public void removeCartItem(int  cartItem) {
		delegate.removeCartItem(cartItem);		
	}

}
