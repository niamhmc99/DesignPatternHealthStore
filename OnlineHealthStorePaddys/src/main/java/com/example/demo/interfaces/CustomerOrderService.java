package com.example.demo.interfaces;

import com.example.demo.model.CustomerOrder;

public interface CustomerOrderService {
	
	void addCustomerOrder(CustomerOrder customerOrder);
	double getCustomerOrderTotal(int shoppingCartId);
	CustomerOrder findById(int orderId);
}
