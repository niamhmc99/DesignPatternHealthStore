package com.example.demo.interfaces;

import java.util.Optional;

import com.example.demo.model.ShoppingCart;

public interface ShoppingCartService {

	Optional<ShoppingCart> getCartByCartId(int cartId);
	void saveShoppingCart(ShoppingCart shoppingCart);

}
