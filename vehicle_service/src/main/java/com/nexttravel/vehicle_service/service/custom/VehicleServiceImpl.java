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

    private static VehicleDto toVehicleDto(Vehicle vehicle) {
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
        vehicleDto.setFee_per_day(vehicle.getFee_per_day());
        vehicleDto.setFee_per_km(vehicle.getFee_per_km());
        return vehicleDto;
    }

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
        System.out.println("VehicleServiceImpl -> getAllVehicles");
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        System.out.println(vehicleList);
        List<VehicleDto> dtoList = new ArrayList<>();
        if (vehicleList.size() == 0) return dtoList;
        for (Vehicle vehicle : vehicleList) {
            VehicleDto vehicleDto = toVehicleDto(vehicle);
          /*  Driver driver = vehicle.getDriver();
            DriverDto driverDto = new DriverDto();
            driverDto.setDriver_id(driver.getDriver_id());
            driverDto.setName(driver.getName());
            driverDto.setContact_no(driver.getContact_no());
            driverDto.setLicense_back(driver.getLicenseBack());
            driverDto.setLicense_front(driver.getLicenseFront());
            vehicleDto.setDriver(driverDto);*/
            dtoList.add(vehicleDto);
        }
        return dtoList;
    }

    @Override
    public VehicleDto getVehicleByVehicleId(String vehicle_id) {
        Vehicle vehicleByVehicleId = vehicleRepository.getVehicleByVehicleId(vehicle_id);
        VehicleDto vehicleDto = toVehicleDto(vehicleByVehicleId);
        Driver driver = vehicleByVehicleId.getDriver();
        DriverDto driverDto = new DriverDto();
        driverDto.setDriver_id(driver.getDriver_id());
        driverDto.setName(driver.getName());
        driverDto.setContact_no(driver.getContact_no());
        driverDto.setLicense_back(driver.getLicenseBack());
        driverDto.setLicense_front(driver.getLicenseFront());
        vehicleDto.setDriver(driverDto);
        return vehicleDto;
    }

    @Override
    public String getOngoingUserID() {
        String lastVehicleId = vehicleRepository.findLastVehicleId();
        System.out.println("last ongoing vehicle -> " + lastVehicleId);
//        System.out.println(lastInsertedUser);
        if (lastVehicleId.isEmpty()) return "V00001";
        String[] split = lastVehicleId.split("[V]");
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("V%05d", lastDigits));
    }

}
