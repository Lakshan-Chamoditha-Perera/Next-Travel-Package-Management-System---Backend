package com.nexttraverl.guide_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guide {
    @Id
    private String guide_id;
    private String name;
    private String address;
    private int age;
    private String gender;
    private String contact_number;
    private String experience;
    private double man_day_value;
    private final List<byte[]> images_list = new ArrayList<>();
    private String remark;
}
