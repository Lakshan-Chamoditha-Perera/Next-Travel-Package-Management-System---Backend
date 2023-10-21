package com.nexttravel.vehicle_service.controller;

import com.nexttravel.vehicle_service.dto.DriverDto;
import com.nexttravel.vehicle_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/driver")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@RequestPart("license_back") byte[] vehicle_img1, @RequestPart("license_front") byte[] vehicle_img2, @RequestPart("driver") DriverDto driver) {
        System.out.println("DriverController -> " + driver);
        driver.setLicense_back(vehicle_img1);
        driver.setLicense_front(vehicle_img2);
        try {
            validateDriverDetails(driver);
            System.out.println("validated");
            if (driverService.existsDriverByDriverId(driver.getId())) {
                System.out.println("exists");
                return ResponseEntity.badRequest().body("Driver already exists");
            }
            driverService.save(driver);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private void validateDriverDetails(DriverDto driver) {
        if (!Pattern.compile("^D\\d{3,}$").matcher(driver.getId()).matches()) {
            throw new RuntimeException("Invalid driver id");
        }
        if (!Pattern.compile("^[A-Za-z\\s]{3,}$").matcher(driver.getName()).matches()) {
            throw new RuntimeException("Invalid driver name");
        }
        if (!Pattern.compile("^\\d{10}$").matcher(driver.getContact_no()).matches()) {
            throw new RuntimeException("Invalid driver contact number");
        }
        if (driver.getLicense_front().length == 0) {
            throw new RuntimeException("Invalid driver license front image");
        }
        if (driver.getLicense_back().length == 0) {
            throw new RuntimeException("Invalid driver license back image");
        }

        System.out.println("Driver validated");
    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getOngoingUserID() {
        String lastVehicleId = driverService.getOngoingId();
        return ResponseEntity.ok(lastVehicleId);
    }

    @PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> patch(
            @RequestPart("license_back") byte[] vehicle_img1,
            @RequestPart("license_front") byte[] vehicle_img2,
            @RequestPart("driver") DriverDto driver) {
        System.out.println("Driver Patch -> " + driver);
        driver.setLicense_back(vehicle_img1);
        driver.setLicense_front(vehicle_img2);
        try {
            validateDriverDetails(driver);
            System.out.println("validated");
            if (driverService.existsDriverByDriverId(driver.getId())) {
                driverService.save(driver);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Driver not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
