package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartItemRepo;
import com.example.demo.interfaces.CartItemService;
import com.example.demo.model.CartItem;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Override
	public CartItem addCartItem(CartItem cartItem) {
		return cartItemRepo.save(cartItem);		
	}

	@Override
	public void removeCartItem(int cartItemId) {
		cartItemRepo.deleteById(cartItemId);	
	}

	@Override
	public void removeAllCartItems(List<CartItem> cartItems) {
		cartItemRepo.deleteAll(cartItems);		
	}

	public void saveCartItem(CartItem cartItem) {
		cartItemRepo.save(cartItem);
	}

	public CartItem findByCartItemId(int cartItemId) {
		// TODO Auto-generated method stub
		return cartItemRepo.findById(cartItemId).orElse(null);
	}

	public void emptyCart(List<CartItem> list) {
		// TODO Auto-generated method stub
		cartItemRepo.deleteAll(list);
		
	}
}
