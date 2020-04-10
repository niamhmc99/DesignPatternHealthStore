package com.example.demo.interfaces;

import com.example.demo.model.Item;

public interface ItemService {
	public Iterable<Item> findAll();

    public Item save(Item item);

}
