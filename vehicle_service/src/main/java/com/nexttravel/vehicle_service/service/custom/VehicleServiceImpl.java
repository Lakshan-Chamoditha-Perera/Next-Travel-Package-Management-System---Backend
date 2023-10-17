package com.nexttravel.vehicle_service.service.custom;

import com.nexttravel.vehicle_service.dto.VehicleDto;
import com.nexttravel.vehicle_service.repository.VehicleRepository;
import com.nexttravel.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public Boolean deleteVehicle(String vehicle_id) {
        if (vehicleRepository.existsVehicleByVehicleId(vehicle_id)) {
            vehicleRepository.deleteById(vehicle_id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean save(VehicleDto vehicleDto) {
        return null;
    }

    @Override
    public Boolean existsVehicleByVehicleId(String vehicle_id) {
        return vehicleRepository.existsVehicleByVehicleId(vehicle_id);
    }


}
