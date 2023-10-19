package com.nexttravel.vehicle_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private String id;
    private String brand;
    private String category;//Economy,Mid-Range,Luxury,Super-luxury
    private String fuel_type;//petrol or diesel
    private String hybrid_or_non;//hybrid or non-hybrid
    private String fuel_usage; //km/l
    private int seat_capacity;
    private String vehicle_type;//car,van,suv,bus
    private String transmission_type;  //auto or manual
    private String availability;//available or not-available
    private String remark;
    private List<byte[]> imageList = new ArrayList<>();
    private DriverDto driver;
    private Double fee_per_day;
    private Double fee_per_km;
}
