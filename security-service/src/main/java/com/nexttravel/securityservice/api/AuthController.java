package com.nexttravel.securityservice.api;

import com.nexttravel.securityservice.entity.User;
import com.nexttravel.securityservice.payload.LoginRequest;
import com.nexttravel.securityservice.payload.responses.JwtResponse;
import com.nexttravel.securityservice.payload.responses.MessageResponse;
import com.nexttravel.securityservice.repo.UserRepository;
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

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("User is already taken!"));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already taken!"));
        }

        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponse("User created successfully!"));
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
