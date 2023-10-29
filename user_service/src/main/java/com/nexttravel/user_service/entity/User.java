package com.nexttravel.user_service.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
public class User {
    @Id
    private String user_id;
    private String username;
    private String nic_no;
    private Integer age;
    private String gender;
    private String email;
    private String contact_number;
    private String remark;
    private String password;
    private String role;
    private byte[] nic_front;
    private byte[] nic_back;

    @CreatedDate
    private LocalDateTime createdTime;
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;
}
