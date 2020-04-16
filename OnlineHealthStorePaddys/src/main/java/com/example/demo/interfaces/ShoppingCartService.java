package com.example.demo.interfaces;

import java.util.Optional;

import com.example.demo.model.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart findById(int userId);
	void saveShoppingCart(ShoppingCart shoppingCart);

}
