package com.nexttravel.booking_service.service;

import com.nexttravel.booking_service.dto.BookingDto;
import com.nexttravel.booking_service.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    String getOngoingPaymentId();
    boolean existsByPaymentId(String id);
    boolean save(PaymentDto paymentDto);

}
