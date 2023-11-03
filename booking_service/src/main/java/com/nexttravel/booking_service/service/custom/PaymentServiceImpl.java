package com.nexttravel.booking_service.service.custom;

import com.nexttravel.booking_service.entity.Payment;
import com.nexttravel.booking_service.repository.PaymentRepository;
import com.nexttravel.booking_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public String getOngoingPaymentId() {
        List<Payment> lastPayment = paymentRepository.getLastPayment();
        return lastPayment.isEmpty()
                ? "PAY0000001"
                : String.format("PAY%07d", Integer.parseInt(lastPayment.get(0).getId().substring(3)) + 1);

    }
}
