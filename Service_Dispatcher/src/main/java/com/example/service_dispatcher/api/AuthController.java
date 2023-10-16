package com.example.service_dispatcher.api;

import com.example.service_dispatcher.dto.UserDTO;
import com.example.service_dispatcher.service.UserService;
import com.example.service_dispatcher.util.JwtUtils;
import com.example.service_dispatcher.util.payload.request.UserLoginRequest;
import com.example.service_dispatcher.util.payload.responses.MessageResponse;
import com.example.service_dispatcher.util.payload.responses.UserJwtResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @PostMapping("user/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
        System.out.println("auth register");
        try {
//            userService.existsByUsername(user.getUsername());
//            userService.save(user);
            userService.get();
            return ResponseEntity.ok(new MessageResponse("Registerd", "User registerd successfully!"));
        }  catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("ERROR", e.getMessage()));
        }
    }

    @PostMapping("user/auth/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt  = jwtUtils.generateJwtToken(authentication);

        Optional<UserDTO> byUsername = userService.findUserByUsername(request.getUsername());
        UserDTO userDTO = byUsername.get();
        System.out.println("Token created...");
        return ResponseEntity.ok(
                new UserJwtResponse(
                        jwt,
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );
    }
}
