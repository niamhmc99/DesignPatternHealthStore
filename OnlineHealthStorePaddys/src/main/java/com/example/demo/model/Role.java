package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	private String role;
	
	//To create relationship with user 
	
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




	

}
