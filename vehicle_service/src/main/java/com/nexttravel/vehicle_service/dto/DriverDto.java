package com.nexttravel.vehicle_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private String id;
    private String name;
    private String contact_no;
    private byte[] license_front;
    private byte[] license_back;
    @ToString.Exclude
    private VehicleDto vehicle;
}
