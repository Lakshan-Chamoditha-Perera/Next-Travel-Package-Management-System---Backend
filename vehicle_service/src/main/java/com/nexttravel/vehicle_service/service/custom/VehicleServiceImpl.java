package com.nexttravel.vehicle_service.service.custom;

import com.nexttravel.vehicle_service.dto.DriverDto;
import com.nexttravel.vehicle_service.dto.VehicleDto;
import com.nexttravel.vehicle_service.entity.Driver;
import com.nexttravel.vehicle_service.entity.Vehicle;
import com.nexttravel.vehicle_service.repository.VehicleRepository;
import com.nexttravel.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.getAll();
        if (vehicleList.isEmpty()) return null;
        List<VehicleDto>dtoList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicle_id(vehicle.getVehicleId());
            vehicleDto.setVehicle_type(vehicle.getVehicleType());
            vehicleDto.setBrand(vehicle.getBrand());
            vehicleDto.setCategory(vehicle.getCategory());
            vehicleDto.setFuel_type(vehicle.getFuelType());
            vehicleDto.setHybrid_or_Non(vehicle.getHybridOrNon());
            vehicleDto.setFuel_usage(vehicle.getFuelUsage());
            vehicleDto.setSeat_capacity(vehicle.getSeatCapacity());
            vehicleDto.setTransmission_type(vehicle.getTransmissionType());
            vehicleDto.setAvailability(vehicle.getAvailability());
            vehicleDto.setRemark(vehicle.getRemark());
            vehicleDto.setImageList(vehicle.getImageList());

            Driver driver = vehicle.getDriver();
            DriverDto driverDto = new DriverDto();
            driverDto.setDriver_id(driver.getDriver_id());
            driverDto.setName(driver.getName());
            driverDto.setContact_no(driver.getContact_no());
            driverDto.setLicense_back(driver.getLicenseBack());
            driverDto.setLicense_front(driver.getLicenseFront());
            vehicleDto.setDriver(driverDto);

        }
        return dtoList;
    }


}
