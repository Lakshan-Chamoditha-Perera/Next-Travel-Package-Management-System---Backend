package com.next_travel.user.service;

public interface UserService {
    Boolean existsUserByUsername(String username);
    String getNewUserID();
}
