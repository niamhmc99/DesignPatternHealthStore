package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.CustomerOrderRepo;
import com.example.demo.interfaces.CustomerOrderService;
import com.example.demo.interfaces.ShoppingCartService;
import com.example.demo.model.CartItem;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.ShoppingCart;

public class CustomerOrderRepoImpl implements CustomerOrderService{
 
	@Autowired
	private CustomerOrderRepo customerOrderRepo;
	
	@Autowired
	private ShoppingCartService cartService;
	
	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderRepo.addCustomerOrder(customerOrder);
	}

	@Override
	public double getCustomerOrderTotal(int shoppingCartid) {
		double grandTotal=0;
		ShoppingCart cart = cartService.findById(shoppingCartid);
		List<CartItem> cartItems = cart.getCartItems();
		
		for(CartItem item: cartItems){
			grandTotal += item.getPrice();
		}
		return grandTotal;
	}
}
