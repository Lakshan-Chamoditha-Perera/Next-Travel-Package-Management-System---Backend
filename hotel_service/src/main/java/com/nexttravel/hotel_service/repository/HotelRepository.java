package com.nexttravel.hotel_service.repository;

import com.nexttravel.hotel_service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface HotelRepository extends JpaRepository<Hotel, String> {
    @Query("SELECT MAX (h.id) FROM Hotel h")
    String findLastHotelId();
}
