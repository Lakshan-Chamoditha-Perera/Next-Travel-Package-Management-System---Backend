package com.nexttravel.vehicle_service.service.custom;

import com.nexttravel.vehicle_service.dto.DriverDto;
import com.nexttravel.vehicle_service.entity.Driver;
import com.nexttravel.vehicle_service.repository.DriverRepository;
import com.nexttravel.vehicle_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;

    @Override
    public Boolean save(DriverDto driver) {
        System.out.println("DriverServiceImpl -> save");

        Driver mapped = modelMapper.map(driver, Driver.class);
//        System.out.println(driver);
        System.out.println("DriverServiceImpl -> mapped");
        driverRepository.save(mapped);
//        System.out.println(driver);
        System.out.println("Driver saved");
        return true;
    }

    @Override
    public Boolean existsDriverByDriverId(String driver_id) {
        System.out.println("DriverServiceImpl -> existsDriverByDriverId");
        boolean b = driverRepository.existsDriverById(driver_id);
        System.out.println("DriverServiceImpl -> existsDriverByDriverId -> " + b);
        return b;
    }

    @Override
    public DriverDto findDriverById(String driver_id) {
        if (driverRepository.existsDriverById(driver_id))
            return modelMapper.map(driverRepository.getDriverById(driver_id), DriverDto.class);
        throw new RuntimeException("Driver not found!");
    }
}
