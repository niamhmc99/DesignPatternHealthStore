package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ItemRepo;
import com.example.demo.interfaces.ItemService;
import com.example.demo.model.CartItem;
import com.example.demo.model.Comment;
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
	
	public void updateStock(ArrayList<CartItem> cartItems) {
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			Item item = cartItem.getItem();

			int stockLevel = (int) (item.getAvailable() - cartItem.getQuantity());
			item.setAvailable(stockLevel);
			this.editItem(item);
		}
	}

	@Override
	public Item findItemById(int itemId) {
		// TODO Auto-generated method stub
		return itemRepo.findByItemId(itemId);
	}

	@Override
	public Item editItem(Item item) {
		itemRepo.save(item);
		Optional<Item> item1 = itemRepo.findById(item.getItemId());
	        if (item1.isPresent()) {
	            Item i = item1.get();
	            i.setTitle(item.getTitle());
	            i.setManufacturer(item.getManufacturer());
	            i.setPrice(item.getPrice());
	            i.setCategory(item.getCategory());
	            i.setImage(item.getImage());
	            i.setAvailable(item.getAvailable());
	            i.setComments(item.getComments());
	            return itemRepo.save(i);
	        } else {
	            return null;
	        }
	}
	
	@Override
	public List<Comment> getCommentForItem(int newId) {
		Item item =this.findItemById(newId);
		
		return item.getComments();
	}

}
