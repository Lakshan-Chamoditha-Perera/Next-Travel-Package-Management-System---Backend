package com.nexttravel.securityservice.service;

import com.nexttravel.securityservice.entity.UserCredential;
import com.nexttravel.securityservice.repo.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserCredentialRepository userCredentialsRepository;

    private final PasswordEncoder passwordEncoder;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        userCredentialsRepository.save(credential);
        return "user added to the system";
    }
}
