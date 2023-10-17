package com.nexttravel.vehicle_service.repository;

import com.nexttravel.vehicle_service.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Boolean existsVehicleByVehicleId(String vehicle_id);
    List<Vehicle> getAll();
}