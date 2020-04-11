package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.CartItem;



public interface CartItemRepo  extends CrudRepository<CartItem, Integer>{
	
	//List<CartItem> findByCartId(int cartItemId);

	//CartItem findByItemItemId(int itemId);
}
