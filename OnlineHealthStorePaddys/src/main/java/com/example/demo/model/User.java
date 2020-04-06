package com.example.demo.model;

import java.util.Set;
import javax.persistence.*;

@Entity
public class User {
	
	@Id 
	@GeneratedValue
	int userId;
	String username, password, email, address, payment;
	@Transient
	private String passwordConfirm;
	

	@ManyToMany
	private Set<Role> roles;
	
//	(cascade = CascadeType.ALL)
//	@JoinTable(name = "USER_ROLES", joinColumns={
//			@JoinColumn(name = "USER_EMAIL", referencedColumnName = "email") }, inverseJoinColumns = {
//					@JoinColumn(name = "ROLE_NAME", referencedColumnName = "name") })
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
	
	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	

}
