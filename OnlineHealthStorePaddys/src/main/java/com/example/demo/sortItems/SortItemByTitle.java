package com.example.demo.sortItems;

import java.util.Collections;
import java.util.List;

import com.example.demo.model.Item;

public class SortItemByTitle implements SortingInterface{

	@Override
	public List<Item> sortAscending(List<Item> items) {
		Collections.sort(items, 
                (item1, item2) -> item1.getTitle().compareTo(item2.getTitle()));
		for(int i=0; i<items.size();i++) {
			System.out.println(i+"st One "+items.get(i).getTitle());
		}
		  return items;
	}

	@Override
	public List<Item> sortDescending(List<Item> items) {
		Collections.sort(items, 
                (item1, item2) -> item1.getTitle().compareTo(item2.getTitle()));
		Collections.reverse(items);
		for(int i=0; i<items.size();i++) {
			System.out.println(i+"st One "+items.get(i).getTitle());
		}
		return items;
	}
	
	

}
