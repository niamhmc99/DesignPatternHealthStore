package com.example.demo.dao.impl;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.ShoppingCartRepo;
import com.example.demo.interfaces.CustomerOrderService;
import com.example.demo.model.ShoppingCart;

public abstract class ShoppingCartRepoImpl implements ShoppingCartRepo{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CustomerOrderService customerOrderService;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public ShoppingCart getCartByCartId(int shoppingCartId) {
		Session session = sessionFactory.openSession();
		ShoppingCart cart = (ShoppingCart) session.get(ShoppingCart.class, shoppingCartId);
		session.close();
		return cart;
	}

	
	public ShoppingCart validate(int shoppingCartId) throws IOException {
		ShoppingCart cart = getCartByCartId(shoppingCartId);
		if (cart == null || cart.getCartItems().size() == 0) {
			throw new IOException(shoppingCartId + "");
		}
		update(cart);
		return cart;
	}

	
	public void update(ShoppingCart cart) {
		int cartId = cart.getShoppingCartId();
		double grandTotal = customerOrderService.getCustomerOrderTotal(cartId);
		cart.setTotalPrice(grandTotal);

		Session session = sessionFactory.openSession();
		session.saveOrUpdate(cart);
		session.flush();
		session.close();		
	}

}
