package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerOrderRepo;
import com.example.demo.interfaces.CustomerOrderService;
import com.example.demo.model.CartItem;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.ShoppingCart;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService{
	
	@Autowired 
	private CustomerOrderRepo orderRepo;
	@Autowired 
	private ShoppingCartServiceImpl cartService;

	@Override
	public CustomerOrder findById(int orderId) {
		// TODO Auto-generated method stub
		return orderRepo.findById(orderId).orElse(null);
	}

	@Override
	public double getCustomerOrderTotal(int cartId) {
		double grandTotal=0;
		ShoppingCart cart = cartService.findById(cartId);
		List<CartItem> cartItems = cart.getCartItems();
		
		for(CartItem item: cartItems){
			grandTotal += item.getPrice();
		}
		return grandTotal;
	}

	@Override
	public void addCustomerOrder(CustomerOrder customerOrder) {
		orderRepo.save(customerOrder);		
	}

	public CustomerOrder editOrder(CustomerOrder order) {
		orderRepo.save(order);
		Optional<CustomerOrder> order1 = orderRepo.findById(order.getOrderId());
	        if (order1.isPresent()) {
	            CustomerOrder o = order1.get();
	            o.setUser(order.getUser());
	            o.setItems(order.getItems());
	            o.setAddress(order.getAddress());
	            o.setOrderTotal(order.getOrderTotal());
	            return orderRepo.save(o);
	        } else {
	            return null;
	        }		
	}

}
