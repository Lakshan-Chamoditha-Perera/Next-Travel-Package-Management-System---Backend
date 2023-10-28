package com.nexttravel.securityservice.service.custom;

import com.nexttravel.securityservice.dto.UserDto;
import com.nexttravel.securityservice.payload.responses.MessageResponse;
import com.nexttravel.securityservice.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    ModelMapper modelMapper;
    @Value("${user-service-url}")
    private String USER_SERVICE_URL;

    @Override
    public Boolean isUserExists(String username) {
        WebClient webClient = WebClient.create(USER_SERVICE_URL + "/check/" + username);
        MessageResponse response = webClient.get().retrieve().bodyToMono(MessageResponse.class).block();
        System.out.println("Auth service : isUserExists() -> " + response.getMessage());
        return (Boolean) response.getData();
    }

    @Override
    public String save(UserDto userDto) {
        System.out.println("Auth service : save() -> " + userDto);
        WebClient webClient = WebClient.create(USER_SERVICE_URL + "/register");
        MessageResponse response = webClient.post().bodyValue(userDto).retrieve().bodyToMono(MessageResponse.class).block();
        System.out.println("Auth service : save() -> " + response.getMessage());
        return (String) response.getData();
    }

    @Override
    public UserDto findByUsername(String username) {
        WebClient webClient = WebClient.create(USER_SERVICE_URL + "/get/" + username);
        MessageResponse response = webClient.get().retrieve().bodyToMono(MessageResponse.class).block();
        if (response.getData() != null) {
            System.out.println("Auth service : findByUsername() -> " + response.getData());
//     map response.data to UserDto
            UserDto userDto = modelMapper.map(response.getData(), UserDto.class);
            return userDto;
        }
        throw new RuntimeException("User not found");
    }
}

