package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.service.UserServiceImpl;


@SpringBootApplication
@ComponentScan({"com.example.demo"})
public class OnlineHealthStorePaddysApplication{

	@Autowired 
	static UserServiceImpl userService;

	
	public static void main(String[] args) {
		 
		SpringApplication.run(OnlineHealthStorePaddysApplication.class, args);
		
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String password = "password";
//		String encodedPassword = passwordEncoder.encode(password);
//
//		System.out.println();
//		System.out.println("Password is         : " + password);
//		System.out.println("Encoded Password is : " + encodedPassword);
		
	}
	


}
