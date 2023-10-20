package com.nexttravel.vehicle_service.repository;

import com.nexttravel.vehicle_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DriverRepository extends JpaRepository<Driver, String> {
    boolean existsDriverById(String driver_id);
    Driver getDriverById(String driver_id);
    @Query("SELECT MAX (d.id) FROM Driver d")
    String findLastId();
}
