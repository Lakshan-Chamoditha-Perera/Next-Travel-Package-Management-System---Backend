package com.nexttravel.user_service.api;

import com.nexttravel.user_service.dto.UserDto;
import com.nexttravel.user_service.payload.responses.MessageResponse;
import com.nexttravel.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody UserDto userDto){
        return null;
    }

    @GetMapping("/check/{username}")
    public ResponseEntity<?>checkUsername(@PathVariable String username){
        Boolean existsUserByUsername = userService.existsUserByUsername(username);
        if(!existsUserByUsername)return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(new MessageResponse("Username already exists",null));
    }
}
