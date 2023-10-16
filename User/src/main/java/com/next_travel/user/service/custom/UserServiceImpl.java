package com.next_travel.user.service.custom;

import com.next_travel.user.entity.User;
import com.next_travel.user.repository.UserRepository;
import com.next_travel.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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

}
