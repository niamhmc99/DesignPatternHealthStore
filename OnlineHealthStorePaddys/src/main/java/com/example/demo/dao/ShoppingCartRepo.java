package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.ShoppingCart;

public interface ShoppingCartRepo extends CrudRepository<ShoppingCart, Integer>{
	ShoppingCart findByUserEmail(String email);
//	ShoppingCart getCartByCartId(int shoppingCartId);
//	ShoppingCart validate(int shoppingCartId) throws IOException;
//	void update(ShoppingCart cart);
}
