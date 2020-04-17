package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.CartItem;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.Item;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.service.CartItemServiceImpl;
import com.example.demo.service.CustomerOrderServiceImpl;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.ShoppingCartServiceImpl;
import com.example.demo.service.UserServiceImpl;

public class CustomerOrderController {

	@Autowired
	private ShoppingCartServiceImpl shoppingCartService;

	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private CustomerOrderServiceImpl customerOrderService;
	
	@Autowired
	private CartItemServiceImpl cartItemsService;
	
	@GetMapping("/placeOrder")
	public String placeOrder(Model model) {
		CustomUserDetail myUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Integer userId=myUserDetails.getUser().getUserId(); 
		User user = userService.findUserById(userId);
		ShoppingCart cart = user.getShoppingCart();
		
		ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
		cartItems.addAll(cart.getCartItems());
		double total = 0;
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			Item item = itemService.findItemById(cartItem.getItem().getItemId());
			total = total + (item.getPrice() * cartItem.getQuantity());
		}

		model.addAttribute("cart", cart);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", total);
		return "order";
		
	}
}
