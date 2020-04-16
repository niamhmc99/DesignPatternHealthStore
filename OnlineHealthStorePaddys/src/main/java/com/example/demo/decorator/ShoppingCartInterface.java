package com.example.demo.decorator;

import java.util.Collection;

import com.example.demo.model.CartItem;

public interface ShoppingCartInterface {
	
	 	public Double getTotalCost();
	    public Collection<CartItemInterface> getShoppingListItems();
	    public void addCartItem(CartItem cartItem);
		public void removeCartItem(int cartItem);
}
