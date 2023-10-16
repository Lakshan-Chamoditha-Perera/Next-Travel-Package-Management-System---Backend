package com.nexttravel.vehicle_service.dto;

import com.nexttravel.vehicle_service.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private String vehicle_id;
    private String brand;
    private String category;
    private String fuel_type;
    private String hybrid_or_Non;
    private String fuel_usage;
    private int seat_capacity;
    private String vehicle_type;
    private String transmission_type;  //auto or manual
    private String availability;
    private String remark;
    private List<Byte[]> imageList = new ArrayList<>();
    private DriverDto driver;
}
