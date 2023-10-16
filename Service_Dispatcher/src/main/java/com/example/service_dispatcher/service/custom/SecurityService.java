package com.example.service_dispatcher.service.custom;

import com.example.service_dispatcher.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SecurityService implements UserDetailsService {
    @Value("${user-service-endpoint}")
    private String customerDataEndpoint;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebClient webClient = WebClient.create(username);
        Mono<UserDTO> responseCustomer = webClient.get()
                .retrieve() // fetch the data
                .bodyToMono(UserDTO.class);
        UserDTO userDTO = responseCustomer.block();

        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found with the given name");
        }
        return org.springframework.security.core.userdetails.User.builder().username(userDTO.getUsername()).password(userDTO.getPassword()).build();

    }
}
