package com.nexttravel.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDetailDto {
    private String hotel_id;
    private int option_id;
    private int no_of_days;
    private double price;
}
