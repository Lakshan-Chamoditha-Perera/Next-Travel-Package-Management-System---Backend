package com.nexttravel.vehicle_service.controller;

import com.nexttravel.vehicle_service.dto.DriverDto;
import com.nexttravel.vehicle_service.entity.Vehicle;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/driver")
@CrossOrigin(origins = "*")
public class DriverController {
    @PostMapping(value = "/save" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(
            @RequestPart("license_back") byte[] vehicle_img1,
            @RequestPart("license_front") byte[] vehicle_img2,
            @RequestPart("driver") DriverDto driver){
        System.out.println(driver);

        return ResponseEntity.ok().build();
    }
}
