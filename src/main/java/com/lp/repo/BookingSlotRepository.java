package com.lp.repo;

import org.springframework.data.repository.CrudRepository;

import com.lp.entities.BookingSlot;
import com.lp.entities.Payment;

public interface BookingSlotRepository extends CrudRepository<BookingSlot, Long> {

}
