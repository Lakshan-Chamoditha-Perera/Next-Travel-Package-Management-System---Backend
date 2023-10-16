package com.nexttravel.user_service.dto;


import com.nexttravel.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String user_id;
    private String username;
    private String nic_no;
    private Integer age;
    private String gender;
    private String email;
    private String contact_number;
    private String remark;
    private String password;
    private Enum<Role> role;
    private byte[] nic_front;
    private byte[] nic_back;
}
