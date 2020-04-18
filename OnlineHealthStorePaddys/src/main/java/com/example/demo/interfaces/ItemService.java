package com.example.demo.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Comment;
import com.example.demo.model.Item;

public interface ItemService {
	public Iterable<Item> findAll();

    public Item save(Item item);
    
	Item findItemById(int itemId);

	Item editItem(Item item);

	List<Comment> getCommentForItem(int newId);

}
