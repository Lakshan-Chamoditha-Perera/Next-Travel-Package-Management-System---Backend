package com.nexttravel.vehicle_service.service;

import com.nexttravel.vehicle_service.dto.DriverDto;
import com.nexttravel.vehicle_service.entity.Driver;

public interface DriverService {
    Boolean save(DriverDto driver);
    Boolean existsDriverByDriverId(String driver_id);
    DriverDto findDriverById(String driver_id);


}
