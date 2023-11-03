package com.nexttravel.booking_service.api;


import com.nexttravel.booking_service.payload.response.StandardMessageResponse;
import com.nexttravel.booking_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping("/get/onGoingPaymentId")
    public StandardMessageResponse getOngoingPaymentId() {
        String ongoingPaymentId = paymentService.getOngoingPaymentId();
        return new StandardMessageResponse("Success", ongoingPaymentId);
    }
}
