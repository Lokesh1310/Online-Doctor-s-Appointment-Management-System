package com.lp.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.entities.Appointment;
import com.lp.entities.BookingSlot;
import com.lp.entities.Department;
import com.lp.entities.Doctor;
import com.lp.entities.User;
import com.lp.exception.InsufficientAmountException;
import com.lp.repo.AppointmentRepository;
import com.lp.repo.BookingSlotRepository;
import com.lp.repo.DoctorRepository;
import com.lp.repo.UserRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	BookingSlotRepository bookingSlotRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    DoctorRepository doctorRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DepartmentService departmentService;
	
	//fetch dates of current month 
	@Override
	public List<LocalDate> getDatesofCurrentMonth() {
		// TODO Auto-generated method stub

		YearMonth yearMonth=YearMonth.now();//return Currect Month

		LocalDate currentDate=LocalDate.now();//currect Date
		
		int daysOfMonth=currentDate.getDayOfMonth();//day it give 1 if monday
		 
		LocalDate start=yearMonth.atDay(daysOfMonth);//ye date dega 1(monday ki)
		LocalDate end=yearMonth.atEndOfMonth();//ye end date dega month ki
		
		List<LocalDate>  datesOfMonth=new  ArrayList<>();
		
		
		while(!start.isAfter(end)) 
		{
			
     datesOfMonth.add(start);
     start=start.plusDays(1);
			
     
 		}		
	    return datesOfMonth;//ye currect date se month ki last date tak return kar dega 
				
		
}

	
//	//fetch available slots for particuler doctor and date
	@Override
	public List<LocalTime> getAvailableSlots(String docName,String date) {
LocalDate  date1=null;

try {
date1=LocalDate.parse(date);
}
catch(Exception e) {
	
	System.out.println(e);
	
}

 Optional<Doctor> doctor=  doctorRepository.findByName(docName);
if(doctor.isPresent()) {
	
	
	LocalTime startTime=LocalTime.of(9, 00,00);
	LocalTime endTime=LocalTime.of(17, 00,00);
	
	
	LocalTime currentTime=startTime;
	
	List<LocalTime> availableSlots=new ArrayList<>();
	
	
	while(currentTime.plusMinutes(30).isBefore(endTime)) {
		
		availableSlots.add(currentTime);
		currentTime=currentTime.plusMinutes(30);
	}


List<LocalTime> bookedSlots=new ArrayList<>();



List<Appointment>  appointments=  appointmentRepository.findByDoctorAndDate(doctor.get(),date1);

for(Appointment appointment:appointments) {
  	
	bookedSlots.add(appointment.getSlot());
}

availableSlots.removeAll(bookedSlots);
return availableSlots;

}


else {
		return null;}
	}


//	 public List<LocalTime> getAvailableSlots(String doctorName, String date) {
//	        List<LocalTime> availableSlots = new ArrayList<>();
//
//	        // Implement the logic to fetch available slots based on doctorName and date
//	        // Example: Querying a database
//	        if (doctorName.equals("Dr. Kapil Patidar") && date.equals("2024-09-10")) {
//	            availableSlots.add(LocalTime.of(9, 0));
//	            availableSlots.add(LocalTime.of(10, 0));
//	            availableSlots.add(LocalTime.of(11, 0));
//	        }
//
//	        return availableSlots;
//	    }
//	
	@Override
	public void bookAppontment(String id, String name, String docName, String date1, String slot1, String paymentOption) {

		Optional<User> optional;
		try {

		 optional=userService.findById(Integer.parseInt(id));
		}
		catch (Exception e) {

			throw new IllegalArgumentException("Invalid User");
			
			
		}
		
		User user=optional.get();
	
		
		
		
		Optional<Department> optDept= departmentService.findByName(name);
		
		Department department=optDept.get();
		
		
		Optional<Doctor> optDoc=doctorRepository.findByName(docName);
		
	Doctor  doctor= optDoc.get();
	
	
	LocalDate  date=null;

	try {
	date=LocalDate.parse(date1);
	}
	catch(Exception e) {
		
		System.out.println(e);
		
	}
	
	
	LocalTime  slot=null;

	try {
	slot=LocalTime.parse(slot1);
	}
	catch(Exception e) {
		
		System.out.println(e);
		
	}


	
	
Timestamp bookingTime=new Timestamp(System.currentTimeMillis());

	
String status =null;
if(paymentOption.equalsIgnoreCase("payNow")) {
	
	
	status="Confirmed";

Double balance=user.getBalance();
if(balance<500) {
	
	throw new InsufficientAmountException("InSufficient Balance,Please load your Wallet");
	
	
}
balance =balance-500;
user.setBalance(balance);

userRepository.save(user);
}

else {
	status="Pending";
}
	Appointment appointment=new Appointment();
	appointment.setDate(date);
	appointment.setSlot(slot);
	appointment.setDoctor(doctor);
	appointment.setUser(user);
	appointment.setBookingTime(bookingTime);
	appointment.setStatus(status);
    appointment.setPaymentOption(paymentOption);

	
    BookingSlot bookingSlot=new  BookingSlot();
    
    bookingSlot.setBooked(status.equalsIgnoreCase("Confirmed")?true:false);
    bookingSlot.setDate(date);
    bookingSlot.setDoctor(doctor);
    bookingSlot.setTimeStart(slot);
    
   Appointment app= appointmentRepository.save(appointment);
   BookingSlot book= bookingSlotRepository.save(bookingSlot);
    
   if(!(app!=null && book!=null )) {
	   
	   throw  new IllegalArgumentException("appointment Not Booked");
   }
    
	}
	

	
	}


