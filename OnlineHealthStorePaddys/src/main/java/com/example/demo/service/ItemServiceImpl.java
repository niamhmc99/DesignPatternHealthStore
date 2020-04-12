package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ItemRepo;
import com.example.demo.interfaces.ItemService;
import com.example.demo.model.Item;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepo itemRepo;
	
	@Override
	public Iterable<Item> findAll() {
		return itemRepo.findAll();
	}

	@Override
	public Item save(Item item) {
		// TODO Auto-generated method stub
		return itemRepo.save(item);
	}

	public List<Item> findByTitle(String title) {
		// TODO Auto-generated method stub
		return itemRepo.findByTitle(title);
	}
	
	public void updateStock(ArrayList<Item> cartItems) {
//		for (int i = 0; i < cartItems.size(); i++) {
//			CartItems cartItem = cartItems.get(i);
//			Item item = cartItem.getItem();
//
//			int stockLevel = item.getStock() - cartItem.getQuantity();
//			item.setStock(stockLevel);
//			this.addItem(item);
//		}
	}

	@Override
	public Item findItemById(int itemId) {
		// TODO Auto-generated method stub
		return itemRepo.findByItemId(itemId);
	}

}
