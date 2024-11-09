package com.lp.service;

import java.util.List;
import java.util.Optional;

import com.lp.dto.DoctorAppontmentDetails;
import com.lp.dto.DoctorDto;
import com.lp.entities.Department;
import com.lp.entities.Doctor;

public interface DoctorService {

	Doctor save(DoctorDto doctor);
	
	Optional<Doctor> findById(long id);
	
	List<Doctor> findAll();

	Iterable<Doctor> findByDepartment(Department d);

	List<DoctorAppontmentDetails> getAllAppointmentsOfDoctor(String id);
}
