package com.nexttravel.hotel_service.api;

import com.nexttravel.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hotel")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/get/lastId")
    public String getOngoingHotelId() {
        System.out.println("getOngoingHotelId() called");
        return hotelService.getOngoingHotelId();
    }
}
