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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		if (itemRepo.findByItemId(item.getItemId())==null) {
			itemRepo.save(item);
		} else {
			itemRepo.update(item);
		}
		return item;
	}
	


	@RequestMapping("/getItemById")
	public String getItemById()
	{
		return "home";
	}
	
	// delete item
	@RequestMapping(value = "/items/{itemId}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("itemId") int itemId, 
		final RedirectAttributes redirectAttributes) {
		itemRepo.deleteById(itemId);
	    System.out.println("Delete by id" + itemId); 
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Item is deleted!");
		return "redirect:/itemList";
	}
	
	// show update form
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("itemId") int itemId, Model model) {
		Optional<Item> item = itemRepo.findById(itemId);
		model.addAttribute("itemForm", item);		
		return "itemForm";

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
	    System.out.println("Sizee" + ((List<Item>) itemService.findAll()).size()); 
	    System.out.println("Find by title" + itemService.findByTitle(title)); 
		System.out.println(model.addAttribute("items", itemService.findAll()));
		return "itemList";
	}
}
