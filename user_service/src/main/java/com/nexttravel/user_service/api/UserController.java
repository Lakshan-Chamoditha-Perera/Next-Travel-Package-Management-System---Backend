package com.nexttravel.user_service.api;

import com.nexttravel.user_service.dto.UserDto;
import com.nexttravel.user_service.payload.exceptions.UserValidationException;
import com.nexttravel.user_service.payload.responses.MessageResponse;
import com.nexttravel.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(
            @RequestPart("nic_front") byte[] nic_front,
            @RequestPart("nic_back") byte[] nic_back,
            @RequestPart("user") UserDto userDto) {
        System.out.println("register");
        try {
//            userDto.setRole("ROLE_USER");
            validateUserdata(userDto);

            validateImages(nic_front, nic_back);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), null));
        }
        Boolean existsUserByUsername = userService.existsUserByUsername(userDto.getUsername());

        userDto.setNic_front(nic_front);
        userDto.setNic_back(nic_back);
        if (existsUserByUsername)
            return ResponseEntity.badRequest().body(new MessageResponse("Username already exists", null));

        userDto.setUser_id(userService.getOngoingUserID());

        return userService.save(userDto) ? ResponseEntity.ok().body(new MessageResponse("User registration successful", null)) : ResponseEntity.badRequest().body(new MessageResponse("User registration failed", null));
    }

    private void validateImages(byte[] nicFront, byte[] nicBack) {
        try {
            if (nicFront == null || nicFront.length == 0) throw new RuntimeException("NIC Front image is required.");
            if (nicBack == null || nicBack.length == 0) throw new RuntimeException("NIC Back image is required.");
        } catch (Exception e) {
            throw new RuntimeException("Error validating images: " + e.getMessage());
        }
    }

    private void validateUserdata(UserDto userDTO) throws RuntimeException {
        if (!Pattern.compile("^([a-zA-Z]+( [a-zA-Z]+)*)$").matcher(userDTO.getUsername()).matches()) {
            System.out.println("invalid username");
            throw new UserValidationException("Invalid userDTO name type!");
        } else if (!(Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(userDTO.getNic_no()).matches() | Pattern.compile("^(\\d{4})(\\d{3})(\\d{4})(\\d{1})$").matcher(userDTO.getNic_no()).matches())) {
            System.out.println("invalid nic pattern");
            throw new UserValidationException("invalid nic pattern");
        } else if (!(Pattern.compile("^\\d+$").matcher(String.valueOf(userDTO.getAge())).matches() && userDTO.getAge() > 0)) {
            System.out.println("invalid age");
            throw new UserValidationException("invalid age");
        } else if (!(userDTO.getGender().equalsIgnoreCase("male") || userDTO.getGender().equalsIgnoreCase("female"))) {
            System.out.println("invalid gender");
            throw new UserValidationException("invalid gender");
        } else if (!Pattern.compile("^([a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,})$").matcher(userDTO.getEmail()).matches()) {
            System.out.println("invalid email");
            throw new UserValidationException("Invalid email address!");
        } else if (!Pattern.compile("^\\d{10}$").matcher(userDTO.getContact_number()).matches()) {
            System.out.println("invalid contact number");
            throw new UserValidationException("Invalid contact number!");
        } else if (userDTO.getPassword() == null) {
            System.out.println("invalid password");
            throw new UserValidationException("Password is null");
        }
        /*else if (userDTO.getRole() == null) {
            System.out.println("invalid role");
            throw new UserValidationException("invalid role");
        }*/
    }
/*
if (!Pattern.compile("^U\\d{3,}$").matcher(userDTO.getUser_id()).matches())
        throw new UserValidationException("Invalid userDTO id type!");
*/

    @GetMapping("/check/{username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username) {
        Boolean existsUserByUsername = userService.existsUserByUsername(username);
        if (!existsUserByUsername) return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(new MessageResponse("Username already exists", null));
    }

    @GetMapping("/getnewid")
    public ResponseEntity<?> getOngoingUserID() {
        String newUserID = userService.getOngoingUserID();
        return ResponseEntity.ok(new MessageResponse(newUserID, null));
    }

    public ResponseEntity<?> deleteUserByUsername(String username) {
        if (!Pattern.compile("^U\\d{3,}$").matcher(username).matches())
            throw new UserValidationException("Invalid username type!");
        Boolean existsUserByUsername = userService.existsUserByUsername(username);
        if (existsUserByUsername) {
            return userService.deleteByUsername(username) ? ResponseEntity.ok().body(new MessageResponse("User deleted successfully", null)) : ResponseEntity.ok().body(new MessageResponse("User deletion failed", null));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User not found", null));
    }


}
