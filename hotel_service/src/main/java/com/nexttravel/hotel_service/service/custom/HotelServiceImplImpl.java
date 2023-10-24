package com.nexttravel.hotel_service.service.custom;

import com.nexttravel.hotel_service.repository.HotelRepository;
import com.nexttravel.hotel_service.service.HotelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImplImpl implements HotelService {
    private final HotelRepository hotelRepository;
    @Override
    public String getOngoingHotelId() {
        String lastHotelId = hotelRepository.findLastHotelId();
        if (lastHotelId==null) return "H00001";
        String[] split = lastHotelId.split("[H]");
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("H%05d", lastDigits));
    }

}
