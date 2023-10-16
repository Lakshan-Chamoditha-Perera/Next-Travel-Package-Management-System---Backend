package com.example.service_dispatcher.util.payload.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
