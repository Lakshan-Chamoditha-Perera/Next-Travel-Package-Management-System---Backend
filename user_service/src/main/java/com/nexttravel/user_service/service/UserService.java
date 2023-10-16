package com.nexttravel.user_service.service;

import com.nexttravel.user_service.dto.UserDto;

public interface UserService {
    Boolean existsUserByUsername(String username);
    String getOngoingUserID();
    Boolean save (UserDto userDto);
}
