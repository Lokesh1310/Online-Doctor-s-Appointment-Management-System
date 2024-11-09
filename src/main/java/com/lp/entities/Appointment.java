	package com.lp.entities;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int appointmentId;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "doctorid")
	private Doctor doctor;

	
	private LocalDate date;
	
	
	private LocalTime slot;
	
	private Timestamp bookingTime;
	
	private String status;
	
	private String paymentOption;

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getSlot() {
		return slot;
	}

	public void setSlot(LocalTime slot) {
		this.slot = slot;
	}

	public Timestamp getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Timestamp bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	
	
	
	
}
