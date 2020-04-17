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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.decorator.CartItemInterface;
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
public class CartItemController {
	
	@Autowired
	private CartItemServiceImpl cartItemService;
	@Autowired
	private ItemServiceImpl itemService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired 
	private ShoppingCartServiceImpl shoppingCartService;
	

    
	@RequestMapping("/shoppingCart/add/{itemId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String addCartItem(@PathVariable(value = "itemId") int itemId, Model model) {
		CustomUserDetail myUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Integer userId=myUserDetails.getUser().getUserId(); //Fetch the custom property in User class
		User user = userService.findUserById(userId);
		System.out.println("Customer Userrr: " + user.getUserId());
		ShoppingCart cart = user.getShoppingCart();
		Item item = itemService.findItemById(itemId);
		
		List<CartItem> cartItems = cart.getCartItems();
		for (int i = 0; i < cartItems.size(); i++) {
			
			CartItem cartItem = cartItems.get(i);
			System.out.println("Item id " + item.getItemId());
			System.out.println("Cart item id " + cartItem.getItem().getItemId());
			if (item.getItemId() == cartItem.getItem().getItemId()) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setPrice(cartItem.getQuantity() * cartItem.getItem().getPrice());
				cartItemService.addCartItem(cartItem);
				return "itemList";
			}
		}
		
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(1);
			cartItem.setItem(item);
			cartItem.setPrice(item.getPrice() * 1);
			cartItem.setShoppingCart(cart);
			cartItemService.addCartItem(cartItem);
		
		String successMessage = "Item Added Successfully";
		model.addAttribute("successMessage", successMessage);

		return "itemList";
	}
	


}
