package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.Role;
import com.example.demo.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	
	   @Autowired
	    private UserRepo userRepo;
	    @Autowired
	    private RoleRepo roleRepo;
	    
	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>((Collection<? extends Role>) roleRepo.findAll()));
        userRepo.save(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

}
