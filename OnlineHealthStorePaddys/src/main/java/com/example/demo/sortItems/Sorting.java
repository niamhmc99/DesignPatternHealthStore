package com.example.demo.sortItems;

import java.util.List;

import com.example.demo.model.Item;

public class Sorting {
	private SortingInterface sorting;
	
	public void setSortingMethod(SortingInterface sorting) {
		this.sorting = sorting;
	}
	
	public SortingInterface getStrategy() {
		return sorting;
	}
	
	public List<Item> ascendingSort(List<Item> items){
		return sorting.sortAscending(items);
	}
	public List<Item> descendingSort(List<Item> items){
		return sorting.sortDescending(items);
	}
}
