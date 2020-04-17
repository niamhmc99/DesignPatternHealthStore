package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.example.demo.payment.PaymentMethod;

@Entity
public class CustomerOrder {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	int orderId;
	
	@ManyToOne (cascade = CascadeType.ALL)
	private User user;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Item> items;
	
	private double orderTotal;
	private String paymentMethod;
	private String address;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		items.add(item);
		
	}
	public boolean pay(PaymentMethod method, ShoppingCart shoppingCart) {
		double totalCost = shoppingCart.calculateTotal();
		return method.pay(totalCost);
	}

}
