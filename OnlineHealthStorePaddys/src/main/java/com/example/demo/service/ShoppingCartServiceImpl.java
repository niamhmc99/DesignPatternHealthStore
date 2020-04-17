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
	public void saveShoppingCart(ShoppingCart shoppingCart) {
		cartRepo.save(shoppingCart);
	}

	@Override
	public ShoppingCart findById(int id) {
		// TODO Auto-generated method stub
		return cartRepo.findById(id).orElse(null);
	}



}
