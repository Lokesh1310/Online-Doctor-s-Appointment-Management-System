package com.lp.service;

import java.util.Optional;

import com.lp.entities.User;

public interface UserService {

	
	User save(User user);
	Optional<User> findById(int id);
	Iterable<User> findAll();
	User findByEmailAndPassword(String email, String password);
}
