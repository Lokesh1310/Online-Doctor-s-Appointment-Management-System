package com.lp.dto;

import lombok.Data;

@Data
public class DoctorDto {

	
	
	private String name;
    private String address;
    private String speciality;
    private Long department;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}		
	public Long getDepartment() {
		return department;
	}
	public void setDepartment(Long department) {
		this.department = department;
	}
    
    
    
}
