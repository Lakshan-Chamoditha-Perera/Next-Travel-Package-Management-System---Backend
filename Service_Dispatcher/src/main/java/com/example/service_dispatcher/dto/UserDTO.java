package com.example.service_dispatcher.dto;

import lombok.Data;

@Data
public class UserDTO {
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
