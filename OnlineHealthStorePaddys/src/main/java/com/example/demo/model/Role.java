package com.example.demo.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	private String role;
	
	//To create relationship with user 
	@ManyToMany(mappedBy="roles")
	private Set<User> users;
	
	public Role() {
		
	}

	public Role(String role) {
		super();
		this.role = role;
	}
	
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}




	

}
