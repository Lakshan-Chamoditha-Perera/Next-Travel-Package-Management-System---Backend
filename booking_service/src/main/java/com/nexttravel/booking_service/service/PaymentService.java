package com.nexttravel.booking_service.service;

import com.nexttravel.booking_service.dto.PaymentDto;

public interface PaymentService {
    String getOngoingPaymentId();
    boolean existsByPaymentId(String id);

    boolean save(PaymentDto paymentDto);

}
