package com.example.demo.sortItems;

import java.util.List;

import com.example.demo.model.Item;

public interface SortingInterface {
	public List<Item> sortAscending(List<Item> items);
	
	public List<Item> sortDescending(List<Item> items);

}
