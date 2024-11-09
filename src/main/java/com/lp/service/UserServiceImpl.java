package com.lp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.entities.User;
import com.lp.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(int id) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(id);
	  if(user.isPresent()) {
		  
		  
		  return user;
	  }
	  
	  return null;
	}

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email, password);
	}

	
	
}
