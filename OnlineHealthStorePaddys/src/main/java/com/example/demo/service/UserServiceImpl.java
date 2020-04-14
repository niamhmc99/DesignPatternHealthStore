package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnits;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ItemRepo;
import com.example.demo.dao.RoleRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.interfaces.UserService;
import com.example.demo.model.Item;
import com.example.demo.model.Role;
import com.example.demo.model.User;

@Service
public class UserServiceImpl implements UserService{
	
//		@Autowired
//		private SessionFactory sessionFactory;
// 
//		public void setSessionFactory(SessionFactory sf) {
//			this.sessionFactory = sf;
//		}
 
	
	   @Autowired
	    private UserRepo userRepo;
	    @Autowired
	    private RoleRepo roleRepo;
	    @Autowired 
	    private ItemRepo itemRepo;
	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
	    
		public void createUser(User user) {
			BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword())); 
			Role userRole = new Role("USER");
			List<Role> roles = new ArrayList<>();
			roles.add(userRole);
			user.setRoles(roles);
			save(user);
		}
		
		public void createAdmin(User user) {
			BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword())); 
			Role userRole = new Role("ADMIN");
			List<Role> roles = new ArrayList<>();
			roles.add(userRole);
			user.setRoles(roles);
			save(user);
		}
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles((List<Role>) roleRepo.findAll());
        userRepo.save(user);
	}
	
//	    EntityManagerFactory entityManagerFactory;
//
//	    @Bean
//	    public SessionFactory getSessionFactory() {
//	        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//	            throw new NullPointerException("factory is not a hibernate factory");
//	        }
//	        return entityManagerFactory.unwrap(SessionFactory.class);
//	    }

	@Override
	public User findByUsername(String username) {
//		Session session = getSessionFactory().getCurrentSession();
//        Criteria crit = session.createCriteria(User.class);
//        crit.add(Restrictions.eq("username", username));
//		return (User) crit.uniqueResult();

		return userRepo.findByUsername(username);
	}
	
	public List<User> findAll() {
//		Session session = getSessionFactory().getCurrentSession();
//        Criteria crit = session.createCriteria(User.class);
//		return crit.list();
		return userRepo.findAll();
    }
	
	   
	    public List<User> search(String query) {
	        List<User> users = (List<User>) userRepo.findAll();
	        List<User> foundUsers = new ArrayList<>();

	        for (User user : users) {
	            if (user.getUsername().equalsIgnoreCase(query) ||
	                    user.getEmail().toLowerCase().contains(query.toLowerCase()) ||
	                    user.getAddress().toLowerCase().contains(query.toLowerCase()))
	                foundUsers.add(user);
	        }

	        return foundUsers;
	    }
	

	@Override
	public Item findOne(int id) {
		return itemRepo.findById(id).orElse(null);	}
	

	@Override
	public User deleteById(int id) {
	    Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            ArrayList<Item> items = (ArrayList<Item>) itemRepo.findAll();
            for(Item item:items){
//                for(Comment comment: user.get().getComments()){
//                    if(item.getComments().contains(comment)){
//                        book.getComments().remove(comment);
//                        bookDAO.save(book);
//                    }
//                }
            }
            userRepo.deleteById(id);
            return user.get();
        } else
            return null;
	}

	@Override
	public User update(User user) {
		 Optional<User> u = userRepo.findById(user.getUserId());
	        if (u.isPresent()) {
	            User user1 = u.get();
	            user1.setUsername(user.getUsername());
	            user1.setEmail(user.getEmail());
	            user1.setPassword(user.getPassword());
	            user1.setAddress(user.getAddress());
	            user1.setPayment(user.getPayment());
	            if (user1.getClass() == user.getClass()) {
	                return userRepo.save(user1);
	            } else {
	                return null;
	            }
	        } else
	            return null;
	}

	@Override
	public User buy(int id, ArrayList<Item> items) {
		  Optional<User> u = userRepo.findById(id);
	        ArrayList<Item> itemArrayList = new ArrayList<>();
	        boolean done = true;
	        if (u.isPresent()) {
	            User user1 = u.get();
	            for (Item item : items) {
	                Optional<Item> i = itemRepo.findById(item.getItemId());
	                if (i.isPresent()) {
	                    Item thisItem = i.get();
	                    if (thisItem.getAvailable() > 0) {
	                    	thisItem.setAvailable(thisItem.getAvailable() - 1);
//	                        bookuserDAO.save(thisBook);
	                        //user1.getItems_purchased().add(thisItem);
	                        itemArrayList.add(thisItem);
//	                        user1 = dao.save(user1);
	                    } else {
	                        done = false;
	                        break;
	                    }
	                }
	            }
	            if (done) {
	                itemRepo.saveAll(itemArrayList);
	                return userRepo.save(user1);
	            } else
	                return null;

	        } else
	            return null;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
        return userRepo.findByUsername(username);
	}


}
