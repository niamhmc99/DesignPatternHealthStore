package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;


@SpringBootApplication
@ComponentScan({"com.example.demo"})
public class OnlineHealthStorePaddysApplication{

	@Autowired 
	static UserServiceImpl userService;

	
	public static void main(String[] args) {
		 
		SpringApplication.run(OnlineHealthStorePaddysApplication.class, args);
		
	}
	


}
