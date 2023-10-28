package com.nexttravel.securityservice.api;

import com.nexttravel.securityservice.dto.UserDto;
import com.nexttravel.securityservice.entity.User;
import com.nexttravel.securityservice.payload.LoginRequest;
import com.nexttravel.securityservice.payload.responses.JwtResponse;
import com.nexttravel.securityservice.payload.responses.MessageResponse;
import com.nexttravel.securityservice.repo.UserRepository;
import com.nexttravel.securityservice.service.AuthService;
import com.nexttravel.securityservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final AuthService authService;

    @PostMapping("/auth/register")
    public MessageResponse registerUser(@RequestBody UserDto userDto) {
        if (authService.isUserExists(userDto.getUsername())) {
            return new MessageResponse("User is already taken!",   true);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return new MessageResponse("Email is already taken!", true);
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        authService.save(userDto);
        return new MessageResponse("User created successfully!", null);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt  = jwtUtils.generateJwtToken(authentication);

        Optional<User> byUsername = userRepository.findByUsername(request.getUsername());
        User user = byUsername.get();
        System.out.println("Token created...");
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                )
        );
    }
    @GetMapping("/auth/validate")
    public Boolean validateToken(@RequestParam("token") String token) {
        return jwtUtils.validateJwtToken(token);
    }
}
