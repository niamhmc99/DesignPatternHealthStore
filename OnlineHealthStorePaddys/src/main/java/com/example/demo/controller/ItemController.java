package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.ItemRepo;
import com.example.demo.model.Item;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.sortItems.SortItemByCategory;
import com.example.demo.sortItems.SortItemByManufacturer;
import com.example.demo.sortItems.SortItemByPrice;
import com.example.demo.sortItems.SortItemByTitle;
import com.example.demo.sortItems.Sorting;


@Controller
public class ItemController {

	@Autowired 
	ItemRepo itemRepo;
	@Autowired
	ItemServiceImpl itemService;
	
	@PutMapping("/item")
	public Item saveOrUpdateItem(@RequestBody Item item)
	{
		return itemRepo.save(item);
	}


	@RequestMapping("getItemById/{itemId}")
	public ModelAndView getProductById(@PathVariable(value = "itemId") int itemId) {
		Item item = itemService.findItemById(itemId);
		return new ModelAndView("itemPage", "itemObj", item);
	}

	@RequestMapping(value = "/item/addItem", method = RequestMethod.GET)
	public String getItemForm(Model model) {
		Item item = new Item();
		model.addAttribute("itemFormObj", item);
		return "addItem";

	}

	@RequestMapping(value = "/item/addItem", method = RequestMethod.POST)
	public String addItem(@Valid @ModelAttribute(value = "itemFormObj") Item item, BindingResult result) {
		if (result.hasErrors())
			return "addItem";
		itemService.save(item);
		return "redirect:/getAllItems";
	}
	
	@RequestMapping(value = "/item/editItem/{itemId}")
	public ModelAndView getEditForm(@PathVariable(value = "itemId") int itemId) {
		Optional<Item> item = itemRepo.findById(itemId);
		return new ModelAndView("editItem", "editItemObj", item);
	}
	

	@RequestMapping(value = "/item/editItem", method = RequestMethod.POST)
	public String editItem(@ModelAttribute(value = "editItemObj") Item item) {
		itemService.editItem(item);
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
		return "editItem";
	}
	
	@GetMapping("/getAllItems")
	public String listItems(Model model){
		model.addAttribute("items", itemService.findAll());
		return "itemList";
	}
	
	@RequestMapping(value ="/AscendingByName", method = RequestMethod.GET)
	public String ascendByName(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByTitle());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.ascendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
	@RequestMapping(value ="/DecendingByName", method = RequestMethod.GET)
	public String decendByName(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByTitle());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.descendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
	@RequestMapping(value ="/AscendingByCategory", method = RequestMethod.GET)
	public String ascendByCategory(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByCategory());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.ascendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
	@RequestMapping(value ="/DecendingByCategory", method = RequestMethod.GET)
	public String decendByCategory(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByCategory());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.descendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
	
	@RequestMapping(value ="/AscendingByPrice", method = RequestMethod.GET)
	public String ascendByPrice(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByPrice());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.ascendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
	@RequestMapping(value ="/DecendingByPrice", method = RequestMethod.GET)
	public String decendByPrice(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByPrice());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.descendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
	
	@RequestMapping(value ="/AscendingByManufacturer", method = RequestMethod.GET)
	public String ascendByMan(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByManufacturer());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.ascendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
	@RequestMapping(value ="/DecendingByManufacturer", method = RequestMethod.GET)
	public String decendByMan(Model model) {
		Sorting sorting = new Sorting();
		sorting.setSortingMethod(new SortItemByManufacturer());
		List<Item> items = (List<Item>) itemService.findAll();
		sorting.descendingSort(items);
		model.addAttribute("items", items);
		return "itemList";
	}
}