package com.nexttravel.booking_service.service.custom;

import com.nexttravel.booking_service.dto.BookingDto;
import com.nexttravel.booking_service.entity.Booking;
import com.nexttravel.booking_service.repository.BookingRepository;
import com.nexttravel.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Override
    public String save(BookingDto bookingDto) {
        Booking map = modelMapper.map(bookingDto, Booking.class);
        Booking save = bookingRepository.save(map);
        System.out.println(map);
        return save.getId();

    }

    @Override
    public boolean existsByBookingId(String bookingId) {
        System.out.println("Exists by booking id "+ bookingId );

        boolean b = bookingRepository.existsById(bookingId);
        System.out.println(b);
        return b;
    }

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
