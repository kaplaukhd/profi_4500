package com.example.myproject.controller;

import com.example.myproject.config.Response;
import com.example.myproject.entity.User;
import com.example.myproject.entity.dto.UserDto;
import com.example.myproject.service.FileUploadService;
import com.example.myproject.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("v1/api")
@Slf4j
@CrossOrigin("*")

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileUploadService uploadService;

    @GetMapping("/user/info")
    public ResponseEntity<UserDto> getUserInfo(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(userService.getUserInfo(user.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user) {
        log.info("user: " + user);
        userService.register(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth/check")
    public Response isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            return new Response("authenticated", LocalDateTime.now(), HttpStatus.OK, 200);
        }
        return new Response("not authenticated", LocalDateTime.now(), HttpStatus.FORBIDDEN, 403);
    }

    @PostMapping("/user/update/avatar")
    public ResponseEntity<Void> updateAvatar(@RequestParam(name = "file") MultipartFile file,
                                               HttpServletResponse response,
                                               @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        User user = userService.findUserByEmail(userDetails.getUsername()).get();
        String time = UUID.randomUUID().toString();
        log.info("file " + file.getContentType());
        uploadService.store(file, time);
        user.setPhotoUrl(time + file.getOriginalFilename());
        userService.saveUser(user);
        response.sendRedirect("/");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody Map<String, String> requestBody) {
        String newPassword = requestBody.get("password");
        User user = userService.findUserByEmail(userDetails.getUsername()).get();
        user.setPassword(newPassword);
        userService.changePassword(user);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/user/change")
    public ResponseEntity<Void> changeUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody Map<String, String> requestBody) {
        User user = userService.findUserByEmail(userDetails.getUsername()).get();
        user.setFirstName(requestBody.get("firstName"));
        user.setLastName(requestBody.get("lastName"));
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }
}
