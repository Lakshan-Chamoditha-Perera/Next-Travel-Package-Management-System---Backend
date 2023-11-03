package com.nexttravel.booking_service.repository;

import com.nexttravel.booking_service.entity.Booking;
import com.nexttravel.booking_service.entity.Payment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository {
    @Query(value = "{}", sort = "{id: -1}", fields = "{id: 1}")
    List<Payment> getLastPayment();
}
