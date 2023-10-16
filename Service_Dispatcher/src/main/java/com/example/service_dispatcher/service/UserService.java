package com.example.service_dispatcher.service;

import com.example.service_dispatcher.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface UserService  {
    Boolean save(UserDTO user) throws JsonProcessingException;

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    Optional<UserDTO> findUserByUsername(String request);

    void get();
}
