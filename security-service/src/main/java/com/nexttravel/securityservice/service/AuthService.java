package com.nexttravel.securityservice.service;

public interface AuthService {
    Boolean isUserExists(String username);
    Boolean save(UserDto userDto);

}
