package com.example.demo.decorator;

import com.example.demo.model.Item;

public interface CartItemInterface {
	
	public int getCartItemId();
    public int getQuantity();
    public double getPrice();
    public Item getItem();
}
