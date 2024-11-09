package com.lp.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lp.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department,Long> {

	Optional<Department> findByName(String name);

	

}
