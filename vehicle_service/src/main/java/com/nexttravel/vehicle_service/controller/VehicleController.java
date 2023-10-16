package com.nexttravel.vehicle_service.controller;

import com.nexttravel.vehicle_service.entity.Vehicle;
import com.nexttravel.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VehicleController {
    private final VehicleService vehicleService;
    @PostMapping(value = "/save" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>register(
            @RequestPart("vehicle_img1") byte[] vehicle_img1,
            @RequestPart("vehicle_img2") byte[] vehicle_img2,
            @RequestPart("vehicle_img3") byte[] vehicle_img3,
            @RequestPart("vehicle_img4") byte[] vehicle_img4,
            @RequestPart("vehicle_img5") byte[] vehicle_img5,
            @RequestPart("vehicle") Vehicle vehicle){
        System.out.println(vehicle);
       return ResponseEntity.ok().build();
    }

}
