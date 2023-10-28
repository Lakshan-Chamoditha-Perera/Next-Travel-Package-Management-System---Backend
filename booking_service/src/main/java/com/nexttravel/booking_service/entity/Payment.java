package com.nexttravel.booking_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Payment {
    @Id
    private String id;
    private String booking_id;
    private double amount;
    private Byte [] receipt;
    private Date payment_date;
}
