package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.decorator.CartItemInterface;
import com.example.demo.model.CartItem;
import com.example.demo.model.Item;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.service.CartItemServiceImpl;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.ShoppingCartServiceImpl;
import com.example.demo.service.UserServiceImpl;

@Controller
public class ShoppingCartController {
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired 
	ItemServiceImpl itemService;
	
	@Autowired
	CartItemServiceImpl cartItemService;
	
	@Autowired 
	ShoppingCartServiceImpl shoppingCartService;
	
	ShoppingCart cart = new ShoppingCart();
	
	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public String viewCart(Model model) {
		System.out.println("\n Cart Size now " + cart.getCartItems().size());
		model.addAttribute("cartPrice", cart.calculateTotal());
		model.addAttribute("lists", cart.getCartItems());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		
		ArrayList<CartItemInterface> cart_items = new ArrayList<CartItemInterface>();
		cart_items.addAll(cart.getCartItems());

		double total = 0;
		for (int i = 0; i < cart_items.size(); i++) {
			CartItemInterface cartItem = cart_items.get(i);
			Item item = itemService.findItemById(cartItem.getItem().getItemId());
			total = total + (item.getPrice() * cartItem.getQuantity());
		}		
		model.addAttribute("user",user);
		model.addAttribute("cart", cart);
		model.addAttribute("cartItems", cart_items);
		model.addAttribute("total", total);
		return "usersCart";
	}
	
	@RequestMapping(value = "/removeItem", method = RequestMethod.POST)
	public String reomveItem(Model model, @RequestParam("itemId") String id) {
		System.out.println("ADDED THE ITEM TO THE CART");
		int newId = Integer.parseInt(id);
		Item item = itemService.findItemById(newId);
		for (int i = 0; i < cart.getCartItems().size(); i++) {
//			if (cart.getCartItems().get(i).getId() == item.getItemId()) {
//				cart.removeItemToCart(cart.getCartItems().getItemId(i));
//				break;
//			}
		}
		model.addAttribute("cartPrice", cart.calculateTotal());
		model.addAttribute("lists", cart.getCartItems());
		return "usersCart";
	}


//	@RequestMapping("/cart/add/{itemId}")
//	@ResponseStatus(value = HttpStatus.NO_CONTENT)
//	public void addCartItem(@PathVariable(value = "itemId") int itemId) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.findOne(auth.getName());
//
//		
//		String emailId = user.getUsername();
//		user = userService.findByUsername(emailId);
//		ShoppingCart cart = user.getShoppingCart();
//		List<CartItem> cartItems = (List<CartItem>) cart.getCartItems();
//		Item item = itemService.findItemById(itemId);
//		for (int i = 0; i < cartItems.size(); i++) {
//			CartItem cartItem = cartItems.get(i);
//			String itemReceived = String.valueOf(itemId);
////			if (itemReceived = cartItem.getItem().getItemId()) {
////				cartItem.setQuantity(cartItem.getQuantity() + 1);
////				cartItem.setPrice(cartItem.getQuantity() * cartItem.getItem().getPrice());
////				cartItemService.addCartItem(cartItem);
////				return;
////			}
//		}
//		CartItem cartItem = new CartItem();
//		cartItem.setQuantity(1);
//		cartItem.setItem(item);
//		cartItem.setPrice(item.getPrice() * 1);
//		cartItem.setShoppingCart(cart);
//		cartItemService.addCartItem(cartItem);
//	}
//	
//	@GetMapping("/addtocart")
//	public String addToCart(@RequestParam("itemId") int itemId, @RequestParam(defaultValue = "") String title, Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				//userService.findOne(auth.getName());
//	
//		ShoppingCart shoppingCart = shoppingCartService.findByUserEmail(user.getEmail());
//		Item item = itemService.findItemById(itemId);
//
//		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
//		cart_items.addAll(shoppingCart.getCartItems());
//		boolean exist = true;
//
//		for (int i = 0; i < cart_items.size(); i++) {
//			CartItem current = cart_items.get(i);
//			if (current.getItem() == item) {
//
//				int temp = cart_items.get(i).getQuantity();
//				cart_items.get(i).setQuantity(temp+1);
//
//				cartItemService.addCartItem(cart_items.get(i));
//				Set<CartItem> updatedList = new HashSet<>(cart_items);
//				shoppingCart.setCartItems(updatedList);
//				exist = false;
//			}
//		}
//
//		if (exist) {
//			CartItem cartItems = new CartItem(shoppingCart, item, 1);
//			cartItemService.addCartItem(cartItems);
//			cart_items.add(cartItems);
//
//			Set<CartItem> updatedList = new HashSet<>(cart_items);
//			shoppingCart.setCartItems(updatedList);
//		}
//
//		shoppingCartService.saveShoppingCart(shoppingCart);
//
//		String successMessage = "";
//		model.addAttribute("successMessage", successMessage);
//
//		List<Item> items = itemService.findByTitle(title);
//		model.addAttribute("items", items);
//
//		return "views/itemList";
//	}


}
