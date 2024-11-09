package com.lp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.dto.DoctorAppontmentDetails;
import com.lp.dto.DoctorDto;
import com.lp.entities.Department;
import com.lp.entities.Doctor;
import com.lp.service.DoctorService;
@CrossOrigin
@RestController 
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	
	@PostMapping(value="/save" ,  consumes = "application/json", produces="application/json")
		public Doctor saveDoctor(@RequestBody DoctorDto doctorDto) {
		
		return doctorService.save(doctorDto);
	}
	
	@GetMapping
	public List<Doctor> getAll()
	{
		return doctorService.findAll();
	}
	
	
	
	@GetMapping(value="/{id}" )
	public List<DoctorAppontmentDetails> getAllAppointmentOfDoctor(@PathVariable String id){
		
		
		return doctorService.getAllAppointmentsOfDoctor(id);
	} 
}
