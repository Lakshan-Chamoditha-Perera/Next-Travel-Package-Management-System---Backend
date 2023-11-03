package com.nexttravel.booking_service.repository;

import com.nexttravel.booking_service.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface BookingRepository extends MongoRepository<Booking, String> {

    boolean existsById(String id);
    @Query(value = "{}", sort = "{id: -1}", fields = "{id: 1}")
    List<Booking> getLastBookingId();
    List<Booking> findAllByUserAndStatus(String user, String status);

    Booking getBookingById(String id);
}
