package com.nexttravel.vehicle_service.repository;

import com.nexttravel.vehicle_service.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Boolean existsVehicleById(String vehicle_id);
    Vehicle getVehicleById(String vehicle_id);

    @Query("SELECT MAX (v.id) FROM Vehicle v")
    String findLastVehicleId();

    List<Vehicle> findAllByCategory(String category);
}
