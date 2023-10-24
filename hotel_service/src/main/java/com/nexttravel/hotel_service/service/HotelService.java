package com.nexttravel.hotel_service.service;

import com.nexttravel.hotel_service.dto.HotelDto;
import com.nexttravel.hotel_service.entity.Hotel;

import java.util.List;

public interface HotelService {
    String getOngoingHotelId();
    List<HotelDto> getAllHotels();

}
