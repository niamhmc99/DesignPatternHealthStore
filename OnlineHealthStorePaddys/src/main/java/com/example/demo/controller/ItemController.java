package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ItemRepo;
import com.example.demo.model.Item;
import com.example.demo.service.ItemServiceImpl;


@Controller
public class ItemController {

	@Autowired 
	ItemRepo itemRepo;
	@Autowired
	ItemServiceImpl itemService;
//	
//	@RequestMapping("/home")
//	public String home()
//	{
//		System.out.println("hi");
//		return "home";
//	}
//	
//	@GetMapping(path="/items")
//	public Iterable<Item> getItems()
//	{
//		return itemRepo.findAll();
//			
//	}
	
	@PutMapping("/item")
	public Item saveOrUpdateItem(@RequestBody Item item)
	{
		itemRepo.save(item);
		return item;
	}
	
	@RequestMapping("/getItemById")
	public String getItemById()
	{
		return "home";
	}
//	
//	@RequestMapping("/item/{id}")
//	public Optional<Item> getAlien(@PathVariable("id")int itemId)
//	{
//		return itemRepo.findById(itemId);
//		
//	}
	
	@GetMapping("/getAllItems")
	public String listItems(Model model, @RequestParam(defaultValue="")  String title) {
		model.addAttribute("items", itemService.findByTitle(title));
		return "itemList";
	}
	
}
