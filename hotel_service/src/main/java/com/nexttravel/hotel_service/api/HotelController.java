package com.nexttravel.hotel_service.api;

import com.nexttravel.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;


}
