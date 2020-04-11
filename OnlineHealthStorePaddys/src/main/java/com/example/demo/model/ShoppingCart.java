package com.example.demo.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int shoppingCartId;
	
	@OneToMany(mappedBy = "shoppingCart")
	private Set<CartItem> cartItems;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_email", nullable = false)
	private User user;
	
	public double calculateTotal() {
		double total = 0;
		ArrayList<CartItem> cartItems = new ArrayList<>();
		cartItems.addAll(this.getCartItems());
		
		for(int i = 0; i < cartItems.size(); i++) {
			Item item = cartItems.get(i).getItem();
			total += item.getPrice() * cartItems.get(i).getQuantity();
		}
		
		return total;
	}
	
	public ShoppingCart(int shoppingCartId, Set<CartItem> cartItems, User user) {
		super();
		this.shoppingCartId = shoppingCartId;
		this.cartItems = cartItems;
		this.user = user;
	}

	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ShoppingCart() {
		
	}

	

}
