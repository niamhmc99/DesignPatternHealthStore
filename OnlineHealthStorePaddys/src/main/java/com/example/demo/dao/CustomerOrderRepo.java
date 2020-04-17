package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.CustomerOrder;

public interface CustomerOrderRepo extends CrudRepository<CustomerOrder, Integer>{

}
