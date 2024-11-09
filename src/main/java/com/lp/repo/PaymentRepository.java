package com.lp.repo;

import org.springframework.data.repository.CrudRepository;

import com.lp.entities.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
