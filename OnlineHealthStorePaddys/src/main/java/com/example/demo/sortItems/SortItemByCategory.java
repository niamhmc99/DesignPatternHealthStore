package com.example.demo.sortItems;

import java.util.Collections;
import java.util.List;

import com.example.demo.model.Item;

public class SortItemByCategory implements SortingInterface{

	@Override
	public List<Item> sortAscending(List<Item> items) {
		Collections.sort(items, 
                (item1, item2) -> item1.getCategory().compareTo(item2.getCategory()));
		return items;
	}

	@Override
	public List<Item> sortDescending(List<Item> items) {
		Collections.sort(items, 
                (item1, item2) -> item1.getCategory().compareTo(item2.getCategory()));
		Collections.reverse(items);
		return items;
	}

}
