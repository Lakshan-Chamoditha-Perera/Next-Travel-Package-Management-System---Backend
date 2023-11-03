package com.nexttravel.booking_service.service.custom;

import com.nexttravel.booking_service.dto.BookingDto;
import com.nexttravel.booking_service.entity.Booking;
import com.nexttravel.booking_service.payload.response.StandardMessageResponse;
import com.nexttravel.booking_service.repository.BookingRepository;
import com.nexttravel.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    @Value("${vehicle-service-url}")
    private String VEHICLE_SERVER_URL;


    @Override
    public String save(BookingDto bookingDto) {
        Booking map = modelMapper.map(bookingDto, Booking.class);
        Booking save = bookingRepository.save(map);

        if (map.getIs_vehicle_needed().equals("yes")) {
            map.getVehicle_list().forEach(vehicle_id -> {
                StandardMessageResponse standardMessageResponse = updateVehicleAvailability(vehicle_id, "not-available");
                System.out.println(standardMessageResponse);
            });
        }

        return save.getId();
    }

    private StandardMessageResponse updateVehicleAvailability(String vehicleId, String availability) {
        WebClient client = WebClient.create(VEHICLE_SERVER_URL + "/update_availability");
        StandardMessageResponse responseEntityMono = client.patch().header("vehicle_id", vehicleId).header("availability", availability).retrieve().bodyToMono(StandardMessageResponse.class).block();
        System.out.println(vehicleId + ": " + responseEntityMono.getData());
        return responseEntityMono;
    }


    @Override
    public boolean existsByBookingId(String bookingId) {
        System.out.println("Exists by booking id " + bookingId);
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

    @Override
    public int getCountByUserAndStatus(String user_id, String status) {
        List<Booking> allByUserAndStatus = bookingRepository.findAllByUserAndStatus(user_id, status);
        return (allByUserAndStatus != null) ? allByUserAndStatus.size() : 0;
    }

    @Override
    public BookingDto getBookingbyId(String id) {
        Booking bookingById = bookingRepository.getBookingById(id);
        return modelMapper.map(bookingById,BookingDto.class);
    }
}
