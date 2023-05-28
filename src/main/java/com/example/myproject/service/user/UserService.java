package com.example.myproject.service.user;

import com.example.myproject.entity.User;
import com.example.myproject.entity.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);

    void saveUser(User user);

    void removeUser(User user);

    UserDto getUserInfo(String username);
}
