package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.decorator.CartItemInterface;
import com.example.demo.model.CartItem;
import com.example.demo.model.Item;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.service.CartItemServiceImpl;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.UserServiceImpl;

@Controller
public class CartItemController {
	
	@Autowired
	private CartItemServiceImpl cartItemService;
	@Autowired
	private ItemServiceImpl itemService;
	@Autowired
	private UserServiceImpl userService;
	
	
	@RequestMapping("/shoppingCart/add/{itemId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addCartItem(@PathVariable(value = "itemId") int itemId) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		//User user = userService.findOne(loggedInUser.getName());
		String username = loggedInUser.getName();
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String emailId = user.getName();
		ShoppingCart cart = ((CartItem) loggedInUser).getShoppingCart();
		System.out.println(cart);
		List<CartItem> cartItems = (List<CartItem>) cart.getCartItems();
		Item item = itemService.findItemById(itemId);
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
//			if (itemIdInCart= (cartItem.getItem().getItemId())) {
//				cartItem.setQuantity(cartItem.getQuantity() + 1);
//				cartItem.setPrice(cartItem.getQuantity() * cartItem.getItem().getPrice());
//				cartItemService.addCartItem(cartItem);
//				return;
//			}
		}
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setItem(item);
		cartItem.setPrice(item.getPrice() * 1);
		cartItem.setShoppingCart(cart);
		cartItemService.addCartItem(cartItem);
	}

}
