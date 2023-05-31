package com.example.myproject.service.user;

import com.example.myproject.entity.Role;
import com.example.myproject.entity.User;
import com.example.myproject.entity.dto.UserDto;
import com.example.myproject.entity.dto.UserMapper;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserInfo(String username) {
        User user = userRepository.findUserByEmail(username).orElseThrow();
        return userMapper.toDto(user);
    }

    @Override
    public void register(User user) {
        userRepository.findUserByEmail(user.getEmail()).ifPresent(x -> {
            throw new RuntimeException("User already registered");
        });
        Role role = roleService.findRoleByName("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void changePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
