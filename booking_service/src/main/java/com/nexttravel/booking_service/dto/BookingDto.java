package com.nexttravel.booking_service.dto;

import com.nexttravel.booking_service.entity.OptionDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private String id;
    private String guide_id;
    private String user_id;
    private Date starting_date;
    private Date ending_date;
    private Date booked_date;
    private String is_guide_needed;
    private String is_vehicle_needed;
    private int no_of_nights;
    private int no_of_days;
    private int no_of_adults;
    private int no_of_child;
    private double total_price;
    private String status; //pending, paid, cancelled
    private String remark;;
    private List<OptionDetailDto> option_detail_list;
    private List<String> vehicle_list;
    private String category;
}
