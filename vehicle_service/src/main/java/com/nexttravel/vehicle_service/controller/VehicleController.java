package com.nexttravel.vehicle_service.controller;

import com.nexttravel.vehicle_service.dto.VehicleDto;
import com.nexttravel.vehicle_service.entity.Vehicle;
import com.nexttravel.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(
            @RequestPart("vehicle_img1") byte[] vehicle_img1,
            @RequestPart("vehicle_img2") byte[] vehicle_img2,
            @RequestPart("vehicle_img3") byte[] vehicle_img3,
            @RequestPart("vehicle_img4") byte[] vehicle_img4,
            @RequestPart("vehicle_img5") byte[] vehicle_img5,
            @RequestPart("vehicle") VehicleDto vehicleDto) {
        System.out.println(vehicleDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVehicle(@RequestHeader("id") String id) {
        if (Pattern.compile("^V\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid vehicle id");
        try {
            Boolean deleted = vehicleService.deleteVehicle(id);
            return  deleted ? ResponseEntity.ok().build(): ResponseEntity.badRequest().body("Vehicle not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    void post() {
        System.out.println("post");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<VehicleDto> allVehicles = vehicleService.getAllVehicles();
        if (allVehicles.size()==0)return ResponseEntity.badRequest().body("No vehicles found");
        return ResponseEntity.ok().body(allVehicles);
    }
}
