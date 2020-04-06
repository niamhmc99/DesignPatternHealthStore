package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

public interface UserService {
	
	  void save(User user);

	    User findByUsername(String username);

}
