package com.lp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lp.entities.Department;
import com.lp.entities.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	Iterable<Doctor> findByDepartment(Department d);
   Optional<Doctor> findByName(String name);
  	
   List<Doctor> findAll();
   @Query(value = "SELECT u.name AS user_name, d.name AS doctor_name, a.date, a.slot " +
           "FROM Appointment a " +
           "JOIN User u ON u.id = a.userid " +
           "JOIN Doctor d ON d.doctorid = a.doctorid " +
           "WHERE d.doctorid = :id", nativeQuery = true)
   List<Object[]> getAllAppointmentsOfDoctor(@Param("id") Long id);
}
