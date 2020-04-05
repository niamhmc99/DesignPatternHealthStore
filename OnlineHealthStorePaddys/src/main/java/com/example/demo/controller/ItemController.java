package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ItemRepo;
import com.example.demo.model.Item;

@RestController
@Controller
public class ItemController {

	@Autowired 
	ItemRepo itemRepo;
	
	@RequestMapping("/home")
	public String home()
	{
		System.out.println("hi");
		return "home.jsp";
	}
//	
//	@GetMapping(path="/items")
//	public Iterable<Item> getItems()
//	{
//		return itemRepo.findAll();
//			
//	}
	
	@PutMapping("/item")
	public Item saveOrUpdateAlien(@RequestBody Item item)
	{
		itemRepo.save(item);
		return item;
	}
	
	@RequestMapping("/getItemById")
	public String getItemById()
	{
		return "home";
	}
	
	@RequestMapping("/item/{id}")
	public Optional<Item> getAlien(@PathVariable("id")int itemId)
	{
		return itemRepo.findById(itemId);
		
	}
	
	
//	@GetMapping(path="/items")
//	public Iterable<Item> getItems()
//	{
//		return itemRepo.findAll();
//			
//	}
}
