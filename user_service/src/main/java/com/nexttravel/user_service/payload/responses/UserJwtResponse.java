package com.nexttravel.user_service.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJwtResponse {
    private String jwt;
    private String username;
    private String email;
    private String role;
}