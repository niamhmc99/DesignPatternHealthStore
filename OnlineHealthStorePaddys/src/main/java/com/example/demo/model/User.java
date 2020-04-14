package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class User {
	
	@Id 
	@GeneratedValue
	int userId;
	String username, password, email, address, payment;
	@Transient
	private String passwordConfirm;
	

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Role> roles;
	 
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private ShoppingCart shoppingCart; 
	
//	 @LazyCollection(LazyCollectionOption.FALSE)
//	    @ManyToMany(cascade = CascadeType.ALL)
//	    private List<Item> items_purchased = new ArrayList<Item>();
	

	public User () {
		
	}
		
	public User(int userId, String username, String password, String passwordConfirm, String email, String address, String payment) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.payment = payment;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	  public String getPasswordConfirm() {
	        return passwordConfirm;
	    }

	    public void setPasswordConfirm(String passwordConfirm) {
	        this.passwordConfirm = passwordConfirm;
	    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> hashSet) {
		this.roles = hashSet;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
