package com.lp.repo;

import org.springframework.data.repository.CrudRepository;

import com.lp.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);


	
	
}
