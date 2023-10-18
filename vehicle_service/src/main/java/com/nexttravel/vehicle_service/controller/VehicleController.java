package com.nexttravel.vehicle_service.controller;

import com.nexttravel.vehicle_service.dto.VehicleDto;
import com.nexttravel.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
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
        System.out.println("VehicleController -> "+vehicleDto);


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

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        System.out.println("Vehicle Controller -> getAll");
        List<VehicleDto> allVehicles = vehicleService.getAllVehicles();
        System.out.println(allVehicles.size());
        if (allVehicles.size()==0)return ResponseEntity.ok().body("No vehicles found");
        System.out.println("done");
        return ResponseEntity.ok().body(allVehicles);

    }


    @GetMapping("/check/{vehicle_id}")
    public ResponseEntity<?> existsByVehicleId(@PathVariable String vehicle_id) {
        Boolean isExists = vehicleService.existsVehicleByVehicleId(vehicle_id);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(false);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getVehicleByVehicleID(@RequestHeader String vehicle_id) {
        Boolean isExists = vehicleService.existsVehicleByVehicleId(vehicle_id);
        if (!isExists) return ResponseEntity.ok("Vehcile not found !");
        VehicleDto vehicleDto = vehicleService.getVehicleByVehicleId(vehicle_id);
        return ResponseEntity.ok(vehicleDto);
    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getOngoingUserID() {
        String lastVehicleId = vehicleService.getOngoingUserID();
        return ResponseEntity.ok(lastVehicleId);
    }
}
