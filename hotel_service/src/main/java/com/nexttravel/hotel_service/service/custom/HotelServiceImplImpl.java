package com.nexttravel.hotel_service.service.custom;

import com.nexttravel.hotel_service.dto.HotelDto;
import com.nexttravel.hotel_service.entity.Hotel;
import com.nexttravel.hotel_service.repository.HotelRepository;
import com.nexttravel.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImplImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public String getOngoingHotelId() {
        String lastHotelId = hotelRepository.findLastHotelId();
        if (lastHotelId == null) return "H00001";
        String[] split = lastHotelId.split("[H]");
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("H%05d", lastDigits));
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        if (hotelList.size() > 0) {
            return hotelList.stream().map(hotel -> modelMapper.map(hotel, HotelDto.class)).collect(Collectors.toList());
        }
        throw new RuntimeException("No hotels found");
    }

    @Override
    public HotelDto getHotelById(String id) {
        Hotel hotelById = hotelRepository.findHotelById(id);
        return modelMapper.map(hotelById, HotelDto.class);
    }

    @Override
    public boolean save(HotelDto hotelDto) {
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        System.out.println("service-> hotel = " + hotel);
        Hotel save = hotelRepository.save(hotel);
        return save != null;
    }

    @Override
    public boolean existsHotelById(String id) {
        return hotelRepository.existsHotelById(id);
    }

    @Override
    public boolean deleteHotelById(String id) {
        hotelRepository.deleteHotelById(id);
        return true;
    }
}
