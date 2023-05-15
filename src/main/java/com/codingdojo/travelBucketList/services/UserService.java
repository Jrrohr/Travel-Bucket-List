package com.codingdojo.travelBucketList.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.travelBucketList.models.LoginUser;
import com.codingdojo.travelBucketList.models.User;
import com.codingdojo.travelBucketList.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if(result.hasErrors()) {
    		return null;
    	}
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "Email already exists!");
    		return null;
    	}
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "Passwords must match!");
    	    return null;
    	} 
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
        return userRepo.save(newUser);
    }
	
	public User login(LoginUser newLoginObject, BindingResult result) {
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "User not found!");
    		return null;
    	}
    	
    	User user = potentialUser.get();
    	
    	if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	    return null;
    	}
    	if(result.hasErrors()) {
    		return null;
    	}
        return user;
    }
    
    public User findById(Long id) {
    	
        Optional<User> user = userRepo.findById(id);
        
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
    
    public User saveUser(User user) {
    	return userRepo.save(user);
    }
}
