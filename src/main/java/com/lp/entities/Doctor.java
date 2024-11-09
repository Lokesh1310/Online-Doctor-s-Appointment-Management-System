package com.lp.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long doctorid;
	
   private  String name;
   private  String speciality;
   private  String address;
  

//many doctors can work in one department   
   @ManyToOne()
   @JoinColumn(name = "departmentid")
   private Department department;


public Long getDoctorid() {
	return doctorid;
}


public void setDoctorid(Long doctorid) {
	this.doctorid = doctorid;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getSpeciality() {
	return speciality;
}


public void setSpeciality(String speciality) {
	this.speciality = speciality;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public Department getDepartment() {
	return department;
}


public void setDepartment(Department department) {
	this.department = department;
}
   
   
   
}
