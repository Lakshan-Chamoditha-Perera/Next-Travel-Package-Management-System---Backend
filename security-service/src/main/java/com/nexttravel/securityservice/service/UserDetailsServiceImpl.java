package com.nexttravel.securityservice.service;

import com.nexttravel.securityservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = authService.findByUsername(username);
        System.out.println("UserDetailServiceImpl -> " + userDto);
        if (userDto == null) {
            throw new UsernameNotFoundException("User not found with the given username");
        }
        return org.springframework.security.core.userdetails.User.builder().username(userDto.getUsername()).password(userDto.getPassword()).build();
    }
}
