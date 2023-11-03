package com.nexttravel.booking_service.service;

import com.nexttravel.booking_service.dto.BookingDto;

public interface BookingService {
    String save(BookingDto bookingDto);
    boolean existsByBookingId(String bookingId);
    String getOngoingBookingId();
    int getCountByUserAndStatus(String user_id,String status);

    BookingDto getBookingbyId(String id);
}
