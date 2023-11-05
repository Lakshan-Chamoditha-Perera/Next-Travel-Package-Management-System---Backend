package com.nexttravel.booking_service.api;


import com.nexttravel.booking_service.dto.PaymentDto;
import com.nexttravel.booking_service.entity.Payment;
import com.nexttravel.booking_service.payload.response.StandardMessageResponse;
import com.nexttravel.booking_service.repository.PaymentRepository;
import com.nexttravel.booking_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @GetMapping("/get/onGoingPaymentId")
    public StandardMessageResponse getOngoingPaymentId() {
        String ongoingPaymentId = paymentService.getOngoingPaymentId();
        return new StandardMessageResponse("Success", ongoingPaymentId);
    }

    @GetMapping("/get/sum_by_booking_id")
    public StandardMessageResponse method(@RequestHeader String id) {
        Double calculated = paymentRepository.calculateTotalPriceByBookingId(id);
        System.out.println("calculated -> " + calculated);
        return new StandardMessageResponse("success", calculated == null ? 0 : calculated);
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StandardMessageResponse save(@RequestPart("receipt") byte[] receipt, @RequestPart("payment") PaymentDto paymentDto) {
        System.out.println(paymentDto);
        paymentDto.setReceipt(receipt);
        try {
            if (validateData(paymentDto)) {
                if (!paymentService.existsByPaymentId(paymentDto.getId())) {
                    boolean saved = paymentService.save(paymentDto);
                    return new StandardMessageResponse(saved?"Success":"error", paymentDto);
                }
                return new StandardMessageResponse("Failed", "Payment already exists");
            }
        } catch (RuntimeException e) {
            return new StandardMessageResponse("Failed", e.getMessage());
        }
        return new StandardMessageResponse("Failed", "Something went wrong");
    }

    private boolean validateData(PaymentDto paymentDto) {
        if (!Pattern.compile("^PAY\\d{3,}$").matcher(paymentDto.getId()).matches()) {
            throw new RuntimeException("Invalid payment id");
        }
        if (!Pattern.compile("^B\\d{3,}$").matcher(paymentDto.getBooking_id()).matches()) {
            throw new RuntimeException("Invalid booking id");
        }
        if (!Pattern.compile("^\\d+(?:\\.\\d+)?$").matcher(String.valueOf(paymentDto.getAmount())).matches()) {
            throw new RuntimeException("Invalid amount");
        }
        if (paymentDto.getReceipt() == null && paymentDto.getReceipt().length == 0) {
            throw new RuntimeException("Invalid receipt");
        }
        return true;
    }
}
