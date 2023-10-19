package com.nexttravel.vehicle_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String id;
    private String name;
    private String contact_no;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] license_front;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] license_back;
    @OneToOne(mappedBy = "driver")
    @ToString.Exclude
    private Vehicle vehicle;
}
