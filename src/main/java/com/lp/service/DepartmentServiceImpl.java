package com.lp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.entities.Department;
import com.lp.repo.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepository departRepo;
	
	@Override
	public Department save(Department department) {
		
		return departRepo.save(department);
	}

	@Override
	public Iterable<Department> findAll() {
		return departRepo.findAll();
	}

	@Override
	public Optional<Department> findById(long id) {
		// TODO Auto-generated method stub
		return departRepo.findById(id);
	}

	@Override
	public Optional<Department> findByName(String name) {
	
		return departRepo.findByName(name);
		 
		
	}

	
	
	
	
	
}