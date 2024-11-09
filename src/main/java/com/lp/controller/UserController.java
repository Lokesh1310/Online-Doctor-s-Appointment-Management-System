package com.lp.controller;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.entities.Department;
import com.lp.entities.Doctor;
import com.lp.entities.User;
import com.lp.model.LoginRequest;
import com.lp.service.AppointmentService;
import com.lp.service.DepartmentService;
import com.lp.service.DoctorService;
import com.lp.service.UserService;

import lombok.Data;
import responses.LoginResponse;

@RestController
@CrossOrigin
@RequestMapping("/user")

public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	DepartmentService departmentService;
	
	
	@Autowired
	DoctorService doctorService;
	
	
	@Autowired
	AppointmentService appointmentService;
	//get all user
	@GetMapping	
	public Iterable<User> getAll(){
		return userService.findAll();
		
	}

	
	//get user by id
	@GetMapping(value = "/{id}")		
	public Optional<User> userByIdUser(@PathVariable String id ) {		
		
		return userService.findById(Integer.parseInt(id));
	}


   @PostMapping(value = "/save")
   public User saveUser(@RequestBody User user) {
	   
	  return userService.save(user);
   }
	
	
//	//login
//	@GetMapping(value="/{email}/{password}")
//	public ResponseEntity<String> loginApi(@PathVariable String email, @PathVariable String password) {
//		
//			User user=	userService.findByEmailAndPassword(email,password); 
//		
//			
//			if(user!=null) {
//				
//				return ResponseEntity.status(HttpStatus.FOUND).body("http://localhost:8080/user/"+user.getId());
//			}
//			
//			else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Email Or Password");
//	}
//	
	
   @PostMapping(value = "/login")
   public ResponseEntity<LoginResponse> loginApi(@RequestBody LoginRequest loginRequest) {
       User user = userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
       		
       if (user != null) {
           Integer userId = user.getId(); // Ensure this is an Integer
           System.out.println("Login successful for user ID: " + userId); // Log for debugging
           return ResponseEntity.ok(new LoginResponse(userId, "Login successful"));
       } else {
           System.out.println("Login failed for email: " + loginRequest.getEmail()); // Log for debugging
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new LoginResponse(0, "User Email or Password is incorrect"));
       }
   }
   
   //to get all department to book appointment
	@GetMapping(value="{id}/bookAppointment")
	public Iterable<Department> findAllDepartment(){
		
		return departmentService.findAll();
		
		
	}

//	@GetMapping(value="{id}/bookAppointment/{name}")
//	public Iterable<Doctor> findAllDoctorsInDepartment(@PathVariable String name){
//		
//		Optional<Department> optional=departmentService.findByName(name);
//		
//		Department d=optional.get();
//		return doctorService.findByDepartment(d);
//		
//		 
//	}
	
	@GetMapping(value="{id}/bookAppointment/{name}")
	public Iterable<Doctor> findAllDoctorsInDepartment(@PathVariable String name) {
	    Optional<Department> optional = departmentService.findByName(name);
	    if (optional.isPresent()) {
	        Department d = optional.get();
	        return doctorService.findByDepartment(d);
	    } else {
	        throw new OpenApiResourceNotFoundException("Department not found");
	    }
	}
	//fetch dates of current month
	@GetMapping(value="{id}/bookAppointment/{name}/{docName}")
	public List<LocalDate> fetchDatesToBookAppointement(@PathVariable String name,@PathVariable String docName){
		
		return appointmentService.getDatesofCurrentMonth();
		
		 
	}

	
//fetch available slots 
	@GetMapping(value="{id}/bookAppointment/{name}/{docName}/{date}")
	public List<LocalTime> fetchAvailableSlot(@PathVariable String name,@PathVariable String docName,@PathVariable String date){
	
		return appointmentService.getAvailableSlots(docName,date);
		
		 
}

	
//	@GetMapping(value = "{id}/bookAppointment/{name}/{docName}/{date}")
//	public List<LocalTime> fetchAvailableSlot(
//	        @PathVariable("id") String userId,
//	        @PathVariable("name") String departmentName,
//	        @PathVariable("docName") String doctorName,
//	        @PathVariable("date") String date) {
//
//	    // Validate the date parameter
//	    if (date == null || date.equals("undefined") || date.isEmpty()) {
//	        throw new IllegalArgumentException("Date parameter is missing or invalid.");
//	    }
//
//	    // Call the service to get available slots
//	    List<LocalTime> availableSlots = appointmentService.getAvailableSlots(doctorName, date);
//	    return availableSlots;
//	}
 	//book appointment
	@PostMapping(value="{id}/bookAppointment/{name}/{docName}/{date}/{slot}/{paymentOption}")
	public ResponseEntity<String> bookAppointment(@PathVariable String id,@PathVariable String name,@PathVariable String docName,
			@PathVariable String date,@PathVariable String slot,@PathVariable String paymentOption){
		
		
		try { 
			appointmentService.bookAppontment(id,name,docName,date,slot,paymentOption);
	      return 	ResponseEntity.status(HttpStatus.OK).body("Appointment Booked SuccessFully");

		}

		catch (Exception e) {
      return 	ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
}
		 
		
		
		
		
	}

}
