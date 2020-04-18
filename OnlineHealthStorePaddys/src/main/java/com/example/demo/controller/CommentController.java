package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Comment;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.service.CommentServiceImpl;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.UserServiceImpl;

@Controller
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentService;
	@Autowired
	private ItemServiceImpl itemService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/viewComments", method = RequestMethod.POST)
	public String viewComments(Model model,@RequestParam("itemId") String itemId) {
		int newId = Integer.parseInt(itemId);
		model.addAttribute("item", itemService.findItemById(newId));
		model.addAttribute("comments",itemService.getCommentForItem(newId));
		return "searchComments";
	}
	
	@RequestMapping(value = "/submitReview", method = RequestMethod.POST)
	public String submitReview(Model model,@RequestParam("rating") String rating,@RequestParam("comment") String comment,@RequestParam("itemId") String itemId) {
		int newId = Integer.parseInt(itemId);
		int newRating = Integer.parseInt(rating);

		CustomUserDetail myUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Integer userId=myUserDetails.getUser().getUserId(); 
		User user = userService.findUserById(userId);

		Item item = itemService.findItemById(newId);
        System.out.println("The comment is: "+rating);
        Comment user_coment = new Comment(user.getUsername(), comment, newRating);
		item.getComments().add(user_coment);

		commentService.addComment(user_coment);
		itemService.editItem(item);		
		System.out.println("Post 5");

		model.addAttribute("item", itemService.findItemById(newId));
		model.addAttribute("comments",itemService.getCommentForItem(newId));
		return "searchComments";
		
	}

}
