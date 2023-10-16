package com.nexttravel.user_service.payload.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
