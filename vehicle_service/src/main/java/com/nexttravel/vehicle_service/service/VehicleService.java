package com.nexttravel.vehicle_service.service;


import com.nexttravel.vehicle_service.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    Boolean deleteVehicle(String vehicle_id);
    Boolean save(VehicleDto vehicleDto);
    Boolean existsVehicleByVehicleId(String vehicle_id);

    List<VehicleDto> getAllVehicles();
    VehicleDto getVehicleByVehicleId(String vehicle_id);
    String getOngoingUserID();

    List<VehicleDto> getAllVehiclesByCategory(String category);

    boolean updateAvailabilityById(String vehicle_id,String availability);
}
