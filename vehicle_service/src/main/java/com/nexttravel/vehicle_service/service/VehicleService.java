package com.nexttravel.vehicle_service.service;


import com.nexttravel.vehicle_service.dto.VehicleDto;

public interface VehicleService {
    Boolean deleteVehicle(String vehicle_id);
    Boolean save(VehicleDto vehicleDto);
    Boolean existsVehicleByVehicleId(String vehicle_id);
}
