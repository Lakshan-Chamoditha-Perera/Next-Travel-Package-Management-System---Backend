package com.nexttravel.booking_service.api;


import com.nexttravel.booking_service.payload.response.StandardMessageResponse;
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
    @GetMapping("/get/ongingPaymentId")
    public StandardMessageResponse getOngoingPaymentId() {
        return new StandardMessageResponse("Success", "P001");
    }
}
