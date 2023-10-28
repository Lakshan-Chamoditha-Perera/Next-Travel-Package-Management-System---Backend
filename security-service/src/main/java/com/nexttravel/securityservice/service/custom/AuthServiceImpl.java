package com.nexttravel.securityservice.service.custom;

import com.nexttravel.securityservice.payload.responses.MessageResponse;
import com.nexttravel.securityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${user-service-url}")
    private String USER_SERVICE_URL;

    @Override
    public Boolean isUserExists(String username) {
        WebClient webClient = WebClient.create(USER_SERVICE_URL+"/check/"+username);
        MessageResponse response = webClient.get().retrieve().bodyToMono(MessageResponse.class).block();
        System.out.println("User is ->"+response.getMessage());
        return (Boolean) response.getData();
    }


}
