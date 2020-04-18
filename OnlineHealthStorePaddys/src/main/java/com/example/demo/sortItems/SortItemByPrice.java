package com.example.demo.sortItems;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.demo.model.Item;

public class SortItemByPrice implements SortingInterface{

	@Override
	public List<Item> sortAscending(List<Item> items) {
		Collections.sort(items, new Comparator<Item>() {
		    public int compare(Item c1, Item c2) {
		        return Double.compare(c1.getPrice(), c2.getPrice());
		    }
		});

		return items;
	}

	@Override
	public List<Item> sortDescending(List<Item> items) {
		Collections.sort(items, new Comparator<Item>() {
		    public int compare(Item c1, Item c2) {
		        return Double.compare(c1.getPrice(), c2.getPrice());
		    }
		});
		Collections.reverse(items);

		return items;
	}

}
