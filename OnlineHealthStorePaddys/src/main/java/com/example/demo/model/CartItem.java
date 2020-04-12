package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartItemId;
	
	private int quantity;
	private double price;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="itemId")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "shoppingCartId")
	@JsonIgnore
	private ShoppingCart shoppingCart;

	

	public CartItem(int cartItemId, int quantity, double price, Item item, ShoppingCart shoppingCart) {
		super();
		this.cartItemId = cartItemId;
		this.quantity = quantity;
		this.price = price;
		this.item = item;
		this.shoppingCart = shoppingCart;
	}

	public CartItem(ShoppingCart shoppingCart, Item item, int quantity) {
		this.shoppingCart = shoppingCart;
		this.item = item;
		this.quantity = quantity;
		}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quanity) {
		this.quantity = quanity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	public CartItem() {
		
	}
	
	
	


}
