package com.next_travel.user.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    private String user_id;
    private String username;
    private String nic_no;
    private Integer age;
    private String gender;
    private String email;
    private String contact_number;
    private String remark;
    private String password;
    private Enum Role;
    private byte[] nic_front;
    private byte[] nic_back;
}
