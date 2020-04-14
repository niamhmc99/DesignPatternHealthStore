package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.service.UserServiceImpl;


@SpringBootApplication
@ComponentScan({"com.example.demo"})
@EnableJpaRepositories
public class OnlineHealthStorePaddysApplication{
	
	public static void main(String[] args) {
		 
		SpringApplication.run(OnlineHealthStorePaddysApplication.class, args);	
	}
	


}
