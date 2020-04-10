package com.example.demo.interfaces;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.demo.model.Item;
import com.example.demo.model.User;

public interface UserService {
	
	  void save(User user);
	 
	  User findByUsername(String username);
	  
	  
	    public Iterable<User> findAll();

	    public Optional<User> findOne(int id);

	    public User deleteById(int id);

//	    public User create(User user);

	    public User update(User user);

	    public User buy(int id, ArrayList<Item> items);

	    public User login(String email, String password);

	  //  public List<User> checkEmail(String email);
	  
//	  List<User> findAll();
	 
}
