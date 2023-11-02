package com.nexttravel.vehicle_service.repository;

import com.nexttravel.vehicle_service.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableJpaRepositories
@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Boolean existsVehicleById(String vehicle_id);

    Vehicle getVehicleById(String vehicle_id);

    @Query("SELECT MAX (v.id) FROM Vehicle v")
    String findLastVehicleId();

    List<Vehicle> findAllByCategory(String category);

    @Modifying
    @Query("UPDATE Vehicle v SET v.availability = :availability WHERE v.id = :vehicleId")
    void updateAvailabilityById(@Param("vehicleId") String vehicleId, @Param("availability") String availability);
}
