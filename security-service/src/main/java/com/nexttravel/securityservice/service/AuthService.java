package com.nexttravel.securityservice.service;

import com.nexttravel.securityservice.dto.UserDto;

import java.util.Optional;

public interface AuthService {
    Boolean isUserExists(String username);
    String save(UserDto userDto);

    UserDto findByUsername(String username);

}
