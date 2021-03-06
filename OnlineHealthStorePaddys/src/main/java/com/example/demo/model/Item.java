package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Item{
	@Id
	@GeneratedValue
	private int itemId;
	private String title, manufacturer, category, image;
	private double price;
    private long available;
    
	@OneToMany(fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();


	
	
	public Item(int itemId, String title, String manufacturer, String category, double price, String image, long available) {
		super();
		this.itemId = itemId;
		this.title = title;
		this.manufacturer = manufacturer;
		this.category = category;
		this.price = price;
		this.image =image;
		this.available=available;
	}
	public Item() {
		super();
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public long getAvailable() {
		return available;
	}
	public void setAvailable(long available) {
		this.available = available;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	

}