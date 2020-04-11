package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ShoppingCartRepo;
import com.example.demo.interfaces.ShoppingCartService;
import com.example.demo.model.ShoppingCart;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired 
	private ShoppingCartRepo cartRepo;
	
	@Override
	public Optional<ShoppingCart> getCartByCartId(int cartId) {
		return cartRepo.findById(cartId);
	}
	
	@Override
	public void saveShoppingCart(ShoppingCart shoppingCart) {
		cartRepo.save(shoppingCart);
	}

	
	public ShoppingCart findByUserEmail(String email) {
		// TODO Auto-generated method stub
		return cartRepo.findByUserEmail(email);
	}

}
