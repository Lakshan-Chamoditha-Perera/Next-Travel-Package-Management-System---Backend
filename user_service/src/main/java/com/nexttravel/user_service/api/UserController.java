package com.nexttravel.user_service.api;

import com.nexttravel.user_service.dto.UserDto;
import com.nexttravel.user_service.payload.responses.MessageResponse;
import com.nexttravel.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/register" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>register(
            @RequestPart("nic_front") byte[] nic_front,
            @RequestPart("nic_back") byte[] nic_back,
            @RequestPart("user") UserDto userDto){

        System.out.println(userDto);
        Boolean existsUserByUsername = userService.existsUserByUsername(userDto.getUsername());

        if(existsUserByUsername)return ResponseEntity.badRequest().body(
                new MessageResponse("Username already exists",null));
        Boolean save = userService.save(userDto);
        return save?ResponseEntity.ok().body(
                new MessageResponse("User registration successful",null)
        ):ResponseEntity.badRequest().body(
                new MessageResponse("User registration failed",null)
        );
    }

    @GetMapping("/check/{username}")
    public ResponseEntity<?>checkUsername(@PathVariable String username){
        Boolean existsUserByUsername = userService.existsUserByUsername(username);
        if(!existsUserByUsername) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(
                new MessageResponse("Username already exists",null)
        );
    }
}
