package com.nexttravel.securityservice.service;

import com.nexttravel.securityservice.dto.UserDto;

public interface AuthService {
    Boolean isUserExists(String username);
    String save(UserDto userDto);

}
