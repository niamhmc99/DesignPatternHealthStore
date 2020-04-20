package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CartItem;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Item;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.payment.MasterCard;
import com.example.demo.payment.Visa;
import com.example.demo.service.CartItemServiceImpl;
import com.example.demo.service.CustomerOrderServiceImpl;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.UserServiceImpl;

@Controller
public class CustomerOrderController {

	@Autowired 
	private CustomerOrderServiceImpl orderService;
	
	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private CustomerOrderServiceImpl customerOrderService;
	
	@Autowired
	private CartItemServiceImpl cartItemsService;
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.GET)	
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

		double totalWithDiscount = cart.calculateDiscount(total);
		model.addAttribute("cart", cart);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", total);
		model.addAttribute("totalWithDiscount", totalWithDiscount);

		return "order";
	}
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public String order(Model model, @Valid @ModelAttribute("customerOrder") CustomerOrder customerOrder, @RequestParam("total") double total, HttpServletRequest request) {
		String view = "";
		CustomUserDetail myUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Integer userId=myUserDetails.getUser().getUserId(); 
		User user = userService.findUserById(userId);
		Set<Item> items = new HashSet<>();
		ShoppingCart shoppingCart = user.getShoppingCart();
		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
		cart_items.addAll(shoppingCart.getCartItems());

			items.addAll(items);

			CustomerOrder order = new CustomerOrder();
			order.setUser(user);
			order.setItems(items);
			order.setOrderTotal(total);
			orderService.addCustomerOrder(order);
	
			if (request.getParameter("payment_method").equals("Visa")) {

				Visa visa = new Visa(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));

				if (order.pay(visa, shoppingCart)) {
					order.setPaymentMethod("Visa");
					order.setAddress(user.getAddress());
					customerOrderService.addCustomerOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(shoppingCart.getCartItems());

					view = "visaSuccess";
				} else {
					String visaError = "";
					model.addAttribute("visaError", visaError);
					model.addAttribute("total", total);
					System.out.println("Visa Error");

					view = "order";
				}
			} else if (request.getParameter("payment_method").equals("Mastercard")) {

				MasterCard mastercard = new MasterCard(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));

				if (order.pay(mastercard, shoppingCart)) {
					order.setPaymentMethod("MasterCard");
					customerOrderService.addCustomerOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(shoppingCart.getCartItems());
					view = "masterCardSuccess";
				} else {
					String mastercardError = "";
					model.addAttribute("total", total);
					System.out.println("Master error");
					model.addAttribute("mastercardError", mastercardError);
					view = "order";
				}
			}
			return view;
	}
}
