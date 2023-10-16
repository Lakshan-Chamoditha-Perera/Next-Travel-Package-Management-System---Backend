package com.example.service_dispatcher.service.custom;

import com.example.service_dispatcher.dto.UserDTO;
import com.example.service_dispatcher.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    @Value("${user-service-endpoint}")
    private String customerDataEndpoint;


    @Override
    public Boolean save(UserDTO user) {
        System.out.println("save in userimpl");
        //need validation
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            WebClient webClient = WebClient.builder().baseUrl(customerDataEndpoint).build();
            webClient.post().body(BodyInserters.fromValue(objectMapper.writeValueAsString(user)))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .subscribe(v -> {
                // The data has been saved successfully.
                        System.out.println("saved");
            }, e -> {
                // An error occurred while saving the data.
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean existsByUsername(String username) throws RuntimeException {
        WebClient webClient = WebClient.create(customerDataEndpoint + username);
        Mono<UserDTO> responseCustomer = webClient.get().retrieve() // fetch the data
                .bodyToMono(UserDTO.class);
        UserDTO userDTO = responseCustomer.block();
        if (userDTO != null) throw new RuntimeException("User is already taken!");
        return true;
    }

    @Override
    public Boolean existsByEmail(String email) {
        WebClient webClient = WebClient.create(customerDataEndpoint + email);
        Mono<UserDTO> responseCustomer = webClient.get().retrieve() // fetch the data
                .bodyToMono(UserDTO.class);
        UserDTO userDTO = responseCustomer.block();
        if (userDTO != null) throw new RuntimeException("User email is already taken!");
        return true;
    }

    @Override
    public Optional<UserDTO> findUserByUsername(String request) {
        return Optional.empty();
    }

    @Override
    public void get() {
        WebClient webClient = WebClient.builder()
                .baseUrl(customerDataEndpoint)
                .build();

        Mono<String> responseBody = webClient.get().retrieve().bodyToMono(String.class);

        String response = responseBody.block();

        System.out.println(response);
    }
}
