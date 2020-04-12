package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
		return itemRepo.save(item);
	}


	@RequestMapping(value = "/item/addItem", method = RequestMethod.GET)
	public String getItemForm(Model model) {
		Item item = new Item();
		// New Arrivals
		// set the category as 1 for the Book book
		//item.setItemCategory("Vitamins");
		model.addAttribute("itemFormObj", item);
		return "addItem";

	}

	@RequestMapping(value = "/item/addItem", method = RequestMethod.POST)
	public String addItem(@Valid @ModelAttribute(value = "itemFormObj") Item item, BindingResult result) {
		// Binding Result is used if the form that has any error then it will
		// redirect to the same page without performing any functions
		if (result.hasErrors())
			return "addItem";
		itemService.save(item);
//		MultipartFile image = item.getImage();
//		if (image != null && !image.isEmpty()) {
//			Path path = Paths
//					.get("/OnlineHealthStorePaddys/src/main/webapp/WEB-INF/resource/images/items/"
//							+ item.getItemId() + ".jpg");
//
//			try {
//				image.transferTo(new File(path.toString()));
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
		return "redirect:/getAllItems";
	}
	
	@RequestMapping(value = "/items/{itemId}/delete")
	public String deleteUser(@PathVariable("itemId") int itemId, 
		final RedirectAttributes redirectAttributes) {
		itemRepo.deleteById(itemId);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Item is deleted!");
		return "redirect:/getAllItems";
	}
	

	@RequestMapping(value = "/items/{itemId}/update", method = RequestMethod.GET)
	public String showUpdateItemForm(@PathVariable("itemId") int itemId, Model model) {
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
	    System.out.println("Find by title" + itemService.findByTitle(title)); 
		System.out.println(model.addAttribute("items", itemService.findAll()));
		return "itemList";
	}
}
