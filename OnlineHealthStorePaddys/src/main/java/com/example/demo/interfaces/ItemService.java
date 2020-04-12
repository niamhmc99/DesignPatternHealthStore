package com.example.demo.interfaces;

import java.util.Optional;

import com.example.demo.model.Item;

public interface ItemService {
	public Iterable<Item> findAll();

    public Item save(Item item);
    
	Item findItemById(int itemId);

	void editItem(Item item);

}
