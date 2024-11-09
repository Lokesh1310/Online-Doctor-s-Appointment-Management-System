package com.lp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;


public interface AppointmentService {

	List<LocalDate> getDatesofCurrentMonth();

	List<LocalTime> getAvailableSlots(String docName, String date);

	void bookAppontment(String id, String name, String docName, String date, String slot, String paymentOption);
 
}
