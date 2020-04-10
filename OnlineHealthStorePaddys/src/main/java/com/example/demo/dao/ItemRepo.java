package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Item;

@Repository
public interface ItemRepo extends CrudRepository<Item, Integer>{

	List<Item> findByTitle(String title);

}
