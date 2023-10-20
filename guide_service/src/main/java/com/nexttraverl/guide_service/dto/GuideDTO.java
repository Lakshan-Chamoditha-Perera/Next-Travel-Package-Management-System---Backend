package com.nexttraverl.guide_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDTO {
    private final List<byte[]> images_list = new ArrayList<>();
    private String id;
    private String name;
    private String address;
    private int age;
    private String gender;
    private String contact_number;
    private String experience;
    private double man_day_value;
    private String remark;
}
