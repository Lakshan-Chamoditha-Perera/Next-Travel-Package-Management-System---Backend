package com.nexttravel.vehicle_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {
    @Id
    private String driver_id;
    private String name;
    private String contact_no;
    @Lob
    private byte[] licenseFront;
    @Lob
    private byte[] licenseBack;
    @OneToOne(mappedBy = "driver")
    @ToString.Exclude
    private Vehicle vehicle;
}
