package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.Role;
import com.example.demo.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired 
	UserRepo userRepo;
	
	@Override
  @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User domainUser = userRepo.findByUsername(username);
        if (domainUser == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : domainUser.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));

        }
        CustomUserDetail customUserDetail=new CustomUserDetail();
        customUserDetail.setUser(domainUser);
        customUserDetail.setAuthorities(grantedAuthorities);
        return customUserDetail;

        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
	
	
	}
