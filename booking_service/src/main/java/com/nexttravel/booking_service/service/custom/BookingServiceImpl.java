package com.nexttravel.booking_service.service.custom;

import com.nexttravel.booking_service.entity.Booking;
import com.nexttravel.booking_service.repository.BookingRepository;
import com.nexttravel.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public String getOngoingBookingId() {
        List<Booking> booking = bookingRepository.getLastBookingId();
        if (booking.isEmpty()) return "B00001";
        String lastId = booking.get(0).getId();
        String[] split = lastId.split("[B]");
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("B%05d", lastDigits));
    }
}
