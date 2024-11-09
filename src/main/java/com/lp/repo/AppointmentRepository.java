package com.lp.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lp.entities.Appointment;
import com.lp.entities.Doctor;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

	List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date1);

}
