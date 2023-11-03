package com.nexttravel.booking_service.api;

import com.nexttravel.booking_service.dto.BookingDto;
import com.nexttravel.booking_service.entity.Booking;
import com.nexttravel.booking_service.payload.response.StandardMessageResponse;
import com.nexttravel.booking_service.repository.BookingRepository;
import com.nexttravel.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingController {
    private final BookingService bookingService;

    private final BookingRepository bookingRepository;

    @GetMapping("/get/onGoingBookingId")
    public StandardMessageResponse getOngoingPaymentId() {
        String ongoingBookingId = bookingService.getOngoingBookingId();
        System.out.println("Ongoing booking id: " + ongoingBookingId);
        return new StandardMessageResponse("Success", ongoingBookingId);
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StandardMessageResponse save(@RequestBody BookingDto booking) {
        System.out.println(booking);
        try {
            if (validateData(booking)) {
                if (!bookingService.existsByBookingId(booking.getId())) {
                    bookingService.save(booking);
                    return new StandardMessageResponse("Success", booking);
                }
                return new StandardMessageResponse("Failed", "Booking already exists");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new StandardMessageResponse("Failed", e.getMessage());
        }
        return new StandardMessageResponse("Failed", "Something went wrong");
    }

    private boolean validateData(BookingDto booking) throws RuntimeException {
        if (!Pattern.compile("^B\\d{3,}$").matcher(booking.getId()).matches())
            throw new RuntimeException("Invalid booking id");

        if (!Pattern.compile("^U\\d{3,}$").matcher(booking.getUser()).matches())
            throw new RuntimeException("Invalid user id");

        if (!Pattern.compile("^(Economy|Mid-Range|Luxury|Super-Luxury)$").matcher(booking.getCategory()).matches())
            throw new RuntimeException("Invalid package category");

        if (Pattern.compile("^(yes|no)$").matcher(booking.getIs_guide_needed()).matches()) {
            if (booking.getIs_guide_needed().equals("yes")) {
                if (!Pattern.compile("^G\\d{3,}$").matcher(booking.getGuide_id()).matches())
                    throw new RuntimeException("Invalid guide id");
            }
        } else {
            throw new RuntimeException("Invalid guide needed");
        }

        if (Pattern.compile("^(yes|no)$").matcher(booking.getIs_vehicle_needed()).matches()) {
            if (booking.getIs_vehicle_needed().equals("yes")) {
                if (booking.getVehicle_list() == null || booking.getVehicle_list().isEmpty())
                    throw new RuntimeException("Invalid vehicle list");
            }
        } else {
            throw new RuntimeException("Invalid vehicle needed");
        }

        if (!Pattern.compile("^(pending|paid|cancelled)$").matcher(booking.getStatus()).matches())
            throw new RuntimeException("Invalid package status");

        System.out.println("Validated");
        return true;
    }


    @GetMapping("/get/bookingCount")
    public StandardMessageResponse bookingCount(@RequestHeader String user_id, @RequestHeader String status) {
        return new StandardMessageResponse("success", bookingService.getCountByUserAndStatus(user_id, status));
    }

    @GetMapping("/get")
    public StandardMessageResponse getBookingById(@RequestHeader String id) {
        if (!Pattern.compile("^B\\d{3,}$").matcher(id).matches())
            return new StandardMessageResponse("error", "invalid booking id type");
        if (!bookingService.existsByBookingId(id)) {
            return new StandardMessageResponse("error", "Booking not found!");
        }
        BookingDto bookingDto = bookingService.getBookingbyId(id);
        return new StandardMessageResponse("success", bookingDto);
    }
}
