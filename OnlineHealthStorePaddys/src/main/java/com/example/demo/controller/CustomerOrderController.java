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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CartItem;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Item;
import com.example.demo.model.MasterCard;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.Visa;
import com.example.demo.service.CartItemServiceImpl;
import com.example.demo.service.CustomerOrderServiceImpl;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.ShoppingCartServiceImpl;
import com.example.demo.service.UserServiceImpl;

@Controller
public class CustomerOrderController {

	@Autowired 
	private CustomerOrderServiceImpl orderService;
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
	
	
    // GET: Review Cart to confirm.
	@RequestMapping(value = "/placeOrder", method = RequestMethod.GET)	public String placeOrder(Model model) {
		System.out.print("In first place order method");
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
//	
	@PostMapping("/placeOrder")
	public String order(Model model, @Valid @ModelAttribute("customerOrder") CustomerOrder customerOrder, @RequestParam("total") double total, HttpServletRequest request) {
		System.out.print("In secong post place order method");
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
	
//			if (request.getParameter("payment_method").equals("Visa")) {
//
//				Visa visa = new Visa(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));
//
//				if (order.pay(visa, shoppingCart)) {
//					order.setPaymentMethod("Visa");
//					order.setAddress(user.getShippingAddress());
//					customerOrderService.saveOrder(order);
//					itemService.updateStock(cart_items);
//					cartItemsService.emptyCart(cartItemsService.findByCartId(shoppingCart.getCartId()));
//
//					view = "views/visaSuccess";
//				} else {
//					String visaError = "";
//					model.addAttribute("visaError", visaError);
//					model.addAttribute("total", total);
//					view = "views/order";
//				}
//			} else if (request.getParameter("payment_method").equals("Mastercard")) {
//
//				MasterCard mastercard = new MasterCard(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));
//
//				if (order.pay(mastercard, shoppingCart)) {
//					order.setPaymentMethod("MasterCard");
//					customerOrderService.saveOrder(order);
//					itemService.updateStock(cart_items);
//					cartItemsService.emptyCart(cartItemsService.findByCartId(shoppingCart.getCartId()));
//
//					view = "views/masterSuccess";
//				} else {
//					String mastercardError = "";
//					model.addAttribute("total", total);
//					model.addAttribute("mastercardError", mastercardError);
//					view = "views/order";
//				}
//			}
			return "order";
	}
//	@RequestMapping(value = "/placeOrder", method = RequestMethod.GET)
//	public String payment(Model model) {
//		ShoppingCart cart = new ShoppingCart();
//		System.out.println("\n Full Price of Cart " + cart.calculateTotal());
//		// model.addAttribute("lists", itemService.getCart());
////		model.addAttribute("price", cart.calcTotalCost());
//		if (cart.getTotalPrice() == 0) {
//			cart.setTotalPrice(cart.calculateTotal());
//		}
//		System.out.println("total price"+cart.getTotalPrice());
//
//		CustomUserDetail myUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	    Integer userId=myUserDetails.getUser().getUserId(); 
//		User user = userService.findUserById(userId);
//		CustomerOrder order = new CustomerOrder();
//		customerOrderService.addCustomerOrder(order);
//		CartItem item;
//		for (int i = 0; i < cart.getShoppingListItems().size(); i++) {
//			item = cart.getCartItems().get(i);
//			Item availableItem = itemService.findItemById(item.getCartItemId());
//			System.out.println("\nSTOCK " + availableItem.getAvailable());
//			itemService.editItem(availableItem);
//			availableItem.setAvailable(availableItem.getAvailable() - 1);
//		
//			//order.addItem(item);
//			System.out.println("STOCK " + availableItem.getAvailable());
//			itemService.editItem(availableItem);
//			System.out.println("STOCK " + availableItem.getAvailable());
//		}
//		user.getOrders().add(order);
//		order.setOrderTotal(cart.getTotalPrice());
//
//		customerOrderService.editOrder(order);
//
//		model.addAttribute("lists", itemService.findAll());
//		cart = new ShoppingCart();
//		return "order";
//	}
}
