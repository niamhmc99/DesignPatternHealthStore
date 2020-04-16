package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.model.CartItem;
import com.example.demo.model.ShoppingCart;

public interface CartItemService {
	
//	List<CartItem> findByShoppingCartCartId(int cartId);
	CartItem findByCartItemId(int cartItemId);
	CartItem addCartItem(CartItem cartItem);
	void removeCartItem(int cartItemId);
	void removeAllCartItems(List<CartItem> cartItems);
}
