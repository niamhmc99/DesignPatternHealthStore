package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaces.SecurityService;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired 
	private UserServiceImpl userService;
	
	@Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
    
    @GetMapping("/users")
    public String listUsers(Model model, @RequestParam(defaultValue="")  String username) { 
		model.addAttribute("users", userService.findByUsername(username));
	    System.out.println("Sizee" + userService.findAll().size()); 
	    System.out.println("Find by username" + userService.findByUsername(username)); 
// <-- This line.
		System.out.println(model.addAttribute("users", userService.findAll()));
		return "listOfUsers";
	}
    
//    @RequestMapping(value="/users",  method = RequestMethod.GET)
//    public String listUsers(Model model) { //@RequestParam(defaultValue="")  String name
//		model.addAttribute("users", userService.findAll());
//	    System.out.println("Sizee" + userService.findAll().size()); // <-- This line.
//		System.out.println(model.addAttribute("users", userService.findAll()));
//		return "listOfUsers";
//	}
    
//    List<TextTutorial> listOfTextTutorials = service.findAll();
//    System.out.println(listOfTextTutorials.size()); // <-- This line.
//    model.addAttribute("textTutorialList", listOfTextTutorials);
    
    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
 
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }
    
	

}
