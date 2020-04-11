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

//	public List<CartItem> findByShoppingCartId(int cartItemId) {
//		// TODO Auto-generated method stub
//		return cartItemRepo.findByCartId(cartItemId);
//	}

//	
//	public CartItem findByItemId(int itemId) {
//		return cartItemRepo.findByItemId(itemId);
//	}

	@Override
	public void removeCartItem(int cartItemId) {
		cartItemRepo.deleteById(cartItemId);	
	}

	@Override
	public void removeAllCartItems(List<CartItem> cartItems) {
		cartItemRepo.deleteAll(cartItems);		
	}

}
