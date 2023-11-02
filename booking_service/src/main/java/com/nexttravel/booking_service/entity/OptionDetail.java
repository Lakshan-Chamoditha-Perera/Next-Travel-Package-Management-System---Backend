package com.nexttravel.booking_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDetail {
    String hotel_id;
    int option_id;
    int no_of_days;
    double price;
}
