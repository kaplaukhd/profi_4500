package com.example.myproject.entity.dto;

import com.example.myproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();;
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        userDto.setCountArticles(user.getArticles().size());
        return userDto;
    }
}
