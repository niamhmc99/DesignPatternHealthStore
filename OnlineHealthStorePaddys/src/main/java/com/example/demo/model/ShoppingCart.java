package com.example.demo.model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.discount.Over100EuroDiscount;
import com.example.demo.discount.SecondDiscount;

@Entity
public class ShoppingCart{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int shoppingCartId;
	
	@OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.MERGE)
	private List<CartItem> cartItems;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	private double totalPrice;
		
	
	public double calculateTotal() {
		double total = 0;
		ArrayList<CartItem>cartItems = new ArrayList<>();
		cartItems.addAll(this.getCartItems());
		
		for(int i = 0; i < cartItems.size(); i++) {
			Item item = cartItems.get(i).getItem();
			total += item.getPrice() * cartItems.get(i).getQuantity();
		}
		
		return total;
	}
	

	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> updatedList) {
		this.cartItems = updatedList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ShoppingCart() {
		
	}

	public Collection<CartItem> getShoppingListItems() {
		//This method uses a decorator pattern also. Collections$UnmodifiableCollection is its wrapper. 
        //that class is not visible from this package.        return Collections.unmodifiableCollection(itemList);
        return Collections.unmodifiableCollection(cartItems);
	}


	public void addItemToCart(Item item) {
		List<Item> items = new ArrayList<>();
		items.add(item);
	}

	public void setTotalPrice(double grandTotal) {
		this.totalPrice= grandTotal;		
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public double calculateDiscount(double total) {

		Over100EuroDiscount over100Discount = new Over100EuroDiscount();
		SecondDiscount secondDiscount = new SecondDiscount(over100Discount);
		double discount = secondDiscount.apply(total);
		return discount;
	}
	

}
