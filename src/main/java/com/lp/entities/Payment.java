package com.lp.entities;

import java.security.Timestamp;

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
public class Payment {


	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long payementId;
	
 
	@ManyToOne
	@JoinColumn(name="appointmentId")
	private Appointment appointment;
	
	private double paymentAmount;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	
	private Timestamp paymentTime;
	
}
