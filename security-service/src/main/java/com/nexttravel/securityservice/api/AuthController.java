package com.nexttravel.securityservice.api;

import com.nexttravel.securityservice.dto.UserDto;
import com.nexttravel.securityservice.payload.LoginRequest;
import com.nexttravel.securityservice.payload.responses.JwtResponse;
import com.nexttravel.securityservice.payload.responses.MessageResponse;
import com.nexttravel.securityservice.service.AuthService;
import com.nexttravel.securityservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final AuthService authService;
    @PostMapping("/auth/register")
    public MessageResponse registerUser(@RequestBody UserDto userDto) {
        System.out.println("/auth/register");
        if (authService.isUserExists(userDto.getUsername())) {
            return new MessageResponse("User is already taken!", false);
        }
        if (authService.isUserExistsByEmail(userDto.getEmail())) {
            return new MessageResponse("Email is already taken!", false);
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        authService.save(userDto);
        return new MessageResponse("User created successfully!", true);
    }

    @PostMapping("/auth/login")
    public MessageResponse login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        if (!authService.isUserExists(request.getUsername())) {
            return new MessageResponse("User not found", null);
        }
        try {
            UserDto user = authService.findByUsername(request.getUsername());
            System.out.println("Token created...");
            return new MessageResponse("Login successful", new JwtResponse(jwt, user.getUser_id(), user.getUsername(), user.getEmail()));
        } catch (RuntimeException e) {
            return new MessageResponse(e.getMessage(), null);
        }
    }


    @GetMapping("/auth/validate")
    public Boolean validateToken(@RequestParam("token") String token) {
        return jwtUtils.validateJwtToken(token);
    }
}
