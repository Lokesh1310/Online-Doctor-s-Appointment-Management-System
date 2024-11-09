package com.lp.service;

import java.util.Optional;

import com.lp.entities.Department;

public interface DepartmentService {

	Department save(Department department);
	Iterable<Department> findAll();
   Optional<Department> findById(long id);
Optional<Department> findByName(String name);
}
