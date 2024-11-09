package com.lp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.dto.DoctorAppontmentDetails;
import com.lp.dto.DoctorDto;
import com.lp.entities.Department;
import com.lp.entities.Doctor;
import com.lp.repo.DepartmentRepository;
import com.lp.repo.DoctorRepository;


@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	DepartmentService departmentService;
	
	
	@Override
	public Doctor save(DoctorDto doctorDto) {
		
		
	Optional<Department> optional=departmentService.findById(doctorDto.getDepartment());
//System.out.println("--------------------------"+doctorDto.getDepartmentId());
	Doctor d=new Doctor();
	if(optional.isPresent()) {
		
		d.setName(doctorDto.getName());
		d.setAddress(doctorDto.getAddress());
		d.setSpeciality(doctorDto.getSpeciality());
		d.setDepartment(optional.get());
	d=doctorRepository.save(d);
	}
		
	else {
		
		throw new IllegalArgumentException("Department is not found");
	}	
	
		return d;
	}

	@Override
	public Optional<Doctor> findById(long id) {
		
		return doctorRepository.findById(id);
	}

	@Override
	public List<Doctor> findAll() {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}

	@Override
	public Iterable<Doctor> findByDepartment(Department d) {

 
		return doctorRepository.findByDepartment(d);
	}

	@Override
	public List<DoctorAppontmentDetails> getAllAppointmentsOfDoctor(String id) {
	
	Long docId=Long.parseLong(id); 
	List<Object[]> result=doctorRepository.getAllAppointmentsOfDoctor(docId);
	List<DoctorAppontmentDetails> response =new ArrayList<>();
	
	for(Object [] data:result) {
		
		DoctorAppontmentDetails d=new DoctorAppontmentDetails();
		d.setUserName(String.valueOf(data[0]));
		d.setDoctorName(String.valueOf(data[1]));
        d.setDate(String.valueOf(data[2]));
d.setSlot(String.valueOf(data[3]));
		
		
		response.add(d);
	}
	
	
		return response;

	}

}
