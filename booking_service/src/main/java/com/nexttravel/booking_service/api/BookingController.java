package com.nexttravel.booking_service.api;

import com.nexttravel.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingController {
    private final BookingService bookingService;
    @GetMapping("/get/onGoingBookingId")
    public ResponseEntity<?> getOngoingPaymentId(){
        String ongoingBookingId = bookingService.getOngoingBookingId();
        return ResponseEntity.ok(ongoingBookingId);
    }
}
