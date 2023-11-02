package com.nexttravel.vehicle_service.controller;

import com.nexttravel.vehicle_service.dto.VehicleDto;
import com.nexttravel.vehicle_service.payload.StandardMessageResponse;
import com.nexttravel.vehicle_service.repository.VehicleRepository;
import com.nexttravel.vehicle_service.service.DriverService;
import com.nexttravel.vehicle_service.service.VehicleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleController {
    private final VehicleService vehicleService;
    private final DriverService driverService;
    private final VehicleRepository vehicleRepository;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@RequestPart("vehicle_img1") byte[] vehicle_img1, @RequestPart("vehicle_img2") byte[] vehicle_img2, @RequestPart("vehicle_img3") byte[] vehicle_img3, @RequestPart("vehicle_img4") byte[] vehicle_img4, @RequestPart("vehicle_img5") byte[] vehicle_img5, @RequestPart("vehicle") VehicleDto vehicleDto, @RequestPart("driver_id") String driver_id) {
        System.out.println("VehicleController -> " + vehicleDto);

        vehicleDto.getImageList().add(vehicle_img1);
        vehicleDto.getImageList().add(vehicle_img2);
        vehicleDto.getImageList().add(vehicle_img3);
        vehicleDto.getImageList().add(vehicle_img4);
        vehicleDto.getImageList().add(vehicle_img5);
        try {
//            System.out.println("try in");
//            System.out.println(vehicleDto);
            validateVehicleDetails(vehicleDto);
            System.out.println("validated");
            if (vehicleService.existsVehicleByVehicleId(vehicleDto.getId())) {
//                System.out.println("exists");
                return ResponseEntity.badRequest().body("Vehicle already exists");
            }
            vehicleDto.setDriver(driverService.findDriverById(driver_id));
            vehicleService.save(vehicleDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private void validateVehicleDetails(VehicleDto vehicleDto) {
        if (!Pattern.compile("^V\\d{3,}$").matcher(vehicleDto.getId()).matches())
            throw new RuntimeException("Invalid vehicle id");
        if (!Pattern.compile("^(Economy|Mid-Range|Luxury|Super-Luxury)$").matcher(vehicleDto.getCategory()).matches())
            throw new RuntimeException("Invalid vehicle category");
        if (!Pattern.compile("^(Car|Van|Bus|SUV)$").matcher(vehicleDto.getVehicle_type()).matches())
            throw new RuntimeException("Invalid vehicle type");
        if (!Pattern.compile("^(Petrol|Diesel)$").matcher(vehicleDto.getFuel_type()).matches())
            throw new RuntimeException("Invalid vehicle fuel type");
        if (!Pattern.compile("^(hybrid|non-hybrid)$").matcher(vehicleDto.getHybrid_or_non().toLowerCase()).matches())
            throw new RuntimeException("Invalid vehicle hybrid or non");
        if (!Pattern.compile("^(Automatic|Manual)$").matcher(vehicleDto.getTransmission_type()).matches())
            throw new RuntimeException("Invalid vehicle transmission type");
        if (!Pattern.compile("^(available|not-available)$").matcher(vehicleDto.getAvailability()).matches())
            throw new RuntimeException("Invalid availability!");
        if (!Pattern.compile("^\\d$").matcher(String.valueOf(vehicleDto.getSeat_capacity())).matches())
            throw new RuntimeException("Invalid seat capacity!");
        if (!Pattern.compile("^\\d+(?:\\.\\d+)?$").matcher(String.valueOf(vehicleDto.getFee_per_day())).matches())
            throw new RuntimeException("Invalid fee per day!");
        if (!Pattern.compile("^\\d+(?:\\.\\d+)?$").matcher(String.valueOf(vehicleDto.getFee_per_km())).matches())
            throw new RuntimeException("Invalid fee per km!");
        vehicleDto.getImageList().forEach((element) -> {
            if (element == null || element.length == 0)
                throw new RuntimeException("Invalid or empty image found in the list.");
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVehicle(@RequestHeader("id") String id) {
        System.out.println("VehicleController -> deleteVehicle");
        if (!Pattern.compile("^V\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid vehicle id");
        try {
            Boolean deleted = vehicleService.deleteVehicle(id);
            return deleted ? ResponseEntity.ok().body("Vehicle deleted successfully!") : ResponseEntity.ok().body("Vehicle not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("Vehicle Controller -> getAll");
        List<VehicleDto> allVehicles = vehicleService.getAllVehicles();
        System.out.println(allVehicles.size());
        if (allVehicles.size() == 0) return ResponseEntity.ok().body("No vehicles found");
        System.out.println("done");
        return ResponseEntity.ok().body(allVehicles);

    }

    @GetMapping("/check/")
    public ResponseEntity<?> existsByVehicleId(@RequestHeader String vehicle_id) {
        Boolean isExists = vehicleService.existsVehicleByVehicleId(vehicle_id);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getVehicleByVehicleID(@RequestHeader String vehicle_id) {
        System.out.println("VehicleController -> getVehicleByVehicleID: " + vehicle_id);
        Boolean isExists = vehicleService.existsVehicleByVehicleId(vehicle_id);
        if (!isExists) return ResponseEntity.badRequest().body("Vehicle not found !");
        VehicleDto vehicleDto = vehicleService.getVehicleByVehicleId(vehicle_id);
        return ResponseEntity.ok(vehicleDto);
    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getOngoingUserID() {
        String lastVehicleId = vehicleService.getOngoingUserID();
        return ResponseEntity.ok(lastVehicleId);
    }

    @PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateVehicle(@RequestPart("vehicle_img1") byte[] vehicle_img1, @RequestPart("vehicle_img2") byte[] vehicle_img2, @RequestPart("vehicle_img3") byte[] vehicle_img3, @RequestPart("vehicle_img4") byte[] vehicle_img4, @RequestPart("vehicle_img5") byte[] vehicle_img5, @RequestPart("vehicle") VehicleDto vehicleDto, @RequestPart("driver_id") String driver_id) {
        System.out.println("Patch -> " + vehicleDto);
        vehicleDto.getImageList().add(vehicle_img1);
        vehicleDto.getImageList().add(vehicle_img2);
        vehicleDto.getImageList().add(vehicle_img3);
        vehicleDto.getImageList().add(vehicle_img4);
        vehicleDto.getImageList().add(vehicle_img5);
        try {
            validateVehicleDetails(vehicleDto);
//            System.out.println("validated");
            if (vehicleService.existsVehicleByVehicleId(vehicleDto.getId())) {
//                System.out.println("exists");
                vehicleDto.setDriver(driverService.findDriverById(driver_id));
                vehicleService.save(vehicleDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Vehicle not exists");

        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping(value = "/update_availability")
    public StandardMessageResponse method(@RequestHeader String vehicle_id, @RequestHeader String availability) {
        if (!Pattern.compile("^V\\d{3,}$").matcher(vehicle_id).matches()) {
            return new StandardMessageResponse("Failed", "invalid vehicle id");
        }
        if (!Pattern.compile("^(available|not-available)$").matcher(availability).matches()) {
            return new StandardMessageResponse("Failed", "invalid availability");
        }
        if (!vehicleService.existsVehicleByVehicleId(vehicle_id)) {
            return new StandardMessageResponse("Failed", "vehicle not found");
        }
        vehicleService.updateAvailabilityById(vehicle_id, availability);
        return new StandardMessageResponse("Success", "Availability updated successfully");
    }

}
