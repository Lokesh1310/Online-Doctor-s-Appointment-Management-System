package com.lp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.entities.Department;
import com.lp.service.DepartmentService;

@CrossOrigin
@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService depService;
	
	@PostMapping(value="/save" ,  consumes = "application/json", produces="application/json")
	public Department saveDepartment(@RequestBody Department department)
	{
		return depService.save(department);
	}
	
	@GetMapping
	public Iterable<Department> getAll()
	{
		return depService.findAll();
	}
	
	
	
}
