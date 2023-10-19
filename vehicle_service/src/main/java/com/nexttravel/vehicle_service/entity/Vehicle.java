package com.nexttravel.vehicle_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    @Id
    private String id;
    private String brand;
    private String category;
    private String fuel_type;
    private String hybrid_or_non;
    private String fuel_usage;
    private int seat_capacity;
    private String vehicle_type;
    private String transmission_type;  //auto or manual
    private String availability;
    private String remark;
    private Double fee_per_day;
    private Double fee_per_km;

    @ElementCollection
    @CollectionTable(name = "vehicle_image_data", joinColumns = @JoinColumn(name = "vehicle_id"))
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private List<byte[]> imageList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Driver driver;
}
