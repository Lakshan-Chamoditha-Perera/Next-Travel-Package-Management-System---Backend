package com.nexttravel.booking_service.repository;

import com.nexttravel.booking_service.entity.Booking;
import com.nexttravel.booking_service.entity.Payment;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface PaymentRepository extends MongoRepository<Payment, String>{
    @Query(value = "{}", sort = "{id: -1}", fields = "{id: 1}")
    List<Payment> getLastPayment();

    @Aggregation(pipeline = {
            "{ $match: { booking_id: ?0 } }",
            "{ $group: { _id: null, totalPrice: { $sum: '$amount' } } }"
    })
    Double calculateTotalPriceByBookingId(String bookingId);

    boolean existsById(String id);
}
