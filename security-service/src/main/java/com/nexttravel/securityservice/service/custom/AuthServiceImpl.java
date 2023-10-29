package com.nexttravel.securityservice.service.custom;

import com.nexttravel.securityservice.dto.UserDto;
import com.nexttravel.securityservice.payload.responses.MessageResponse;
import com.nexttravel.securityservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;

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
    public Boolean isUserExistsByEmail(String email) {
        WebClient webClient = WebClient.create(USER_SERVICE_URL + "/checkByEmail/" + email);
        MessageResponse response = webClient.get().retrieve().bodyToMono(MessageResponse.class).block();
        System.out.println("Auth service : isUserExistsByEmail() -> " + response.getMessage());
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

        System.out.println("Auth service : findByUsername() -> " + username);

        WebClient webClient = WebClient.create(USER_SERVICE_URL + "/get/" + username);
        System.out.println("Auth service : findByUsername() -> web client created" );
        MessageResponse response = webClient.get().retrieve().bodyToMono(MessageResponse.class).block();
        System.out.println(response);
        if (response.getData() != null) {
            System.out.println("Auth service : findByUsername() -> " + response.getData());
            UserDto userDto = modelMapper.map(response.getData(), UserDto.class);
            return userDto;
        }
        throw new RuntimeException("User not found");
    }
}

