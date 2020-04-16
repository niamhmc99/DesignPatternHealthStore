package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.demo.model.CartItem;
import com.example.demo.model.CustomUserDetail;
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
	
	@RequestMapping(value = "/viewShoppingCart", method = RequestMethod.GET)
	public String viewCart(Model model) {
		CustomUserDetail myUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Integer userId=myUserDetails.getUser().getUserId(); 
		User user = userService.findUserById(userId);
		ShoppingCart cart = user.getShoppingCart();
		
		List<CartItem> cartItems = cart.getCartItems();		
		cartItems.addAll(cart.getCartItems());
		
		double total = 0;
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			Item item = itemService.findItemById(cartItem.getItem().getItemId());
			total = total + (item.getPrice() * cartItem.getQuantity());
		}		

		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", total);
		return "viewShoppingCart";
	}
	
//	@RequestMapping(value = "/removeItem", method = RequestMethod.POST)
//	public String removeItem(Model model, @RequestParam("itemId") String itemId) {
//		System.out.println("ADDED THE ITEM TO THE CART");
//		int newId = Integer.parseInt(itemId);
//		Item item = itemService.findItemById(newId);
//		for (int i = 0; i < cart.getCartItems().size(); i++) {
////		if (cart.getCartItems().get(i).getId() == item.getItemId()) {
////				cart.removeItemToCart(cart.getCartItems().getItemId(i));
////				break;
////			}
//		}
//		model.addAttribute("cartPrice", cart.calculateTotal());
//		model.addAttribute("lists", cart.getCartItems());
//		return "usersCart";
//	}
	
	@RequestMapping("/shoppingCart/removeCartItem/{cartItemId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String removeCartItem(@PathVariable(value = "cartItemId") int cartItemId) {
		System.out.println("Remove cart item" + cartItemId);
		System.out.println("From cart" + cart);
		cart.removeCartItem(cartItemId);
		return "viewShoppingCart";
	}

	@RequestMapping("/shoppingCart/removeAllItems/{shoppingCartId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String removeAllCartItems(@PathVariable(value = "shoppingCartId") int shoppingCartId) {
		ShoppingCart cart = shoppingCartService.findById(shoppingCartId);
		List<CartItem> cartItems =cart.getCartItems();
		cartItemService.removeAllCartItems(cartItems);
		return "viewShoppingCart";
	}
}
