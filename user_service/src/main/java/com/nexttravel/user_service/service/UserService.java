package com.nexttravel.user_service.service;

import com.nexttravel.user_service.dto.UserDto;

import java.util.List;

public interface UserService {
    Boolean existsUserByUsername(String username);
    String getOngoingUserID();
    Boolean save (UserDto userDto);
    Boolean deleteByUsername (String username);
    List<UserDto> getAllUsersList();
}
