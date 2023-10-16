package com.nexttravel.user_service.service.custom;


import com.nexttravel.user_service.dto.UserDto;
import com.nexttravel.user_service.entity.User;
import com.nexttravel.user_service.repository.UserRepository;
import com.nexttravel.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
   private ModelMapper modelMapper;

    @Override
    public Boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public String getNewUserID() {
        User lastUserId = userRepository.findLastUserId();
        if (lastUserId == null) return "U00001";
        String lastId = lastUserId.getUser_id();
        String[] split = lastId.split("[U]");
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("U%05d", lastDigits));
    }

    @Override
    public Boolean save(UserDto userDto) throws RuntimeException {
        User user = new User();
        user.setUser_id(userDto.getUser_id());
        user.setUsername(userDto.getUsername());
        user.setNic_no(userDto.getNic_no());
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setContact_number(userDto.getContact_number());
        user.setRemark(userDto.getRemark());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setNic_front(userDto.getNic_front());
        user.setNic_back(userDto.getNic_back());
        userRepository.save(user);
        return true;
    }

}
