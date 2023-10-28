package com.nexttravel.booking_service.entity;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String id;
    private String guide_id;
    private String user_id;
    private String category;
    private Date start_date;
    private Date end_date;
    private Date booked_date;
    private int no_of_nights;
    private int no_of_days;
    private int no_of_adults;
    private int no_of_child;
    private double total_price;
    private String status; //pending, paid, cancelled
    private String remark;
    private List<String> package_list;
    private List<String> vehicle_list;
}
