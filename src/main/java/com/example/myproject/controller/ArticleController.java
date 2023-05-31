package com.example.myproject.controller;

import com.example.myproject.entity.Article;
import com.example.myproject.entity.User;
import com.example.myproject.entity.dto.ArticleDto;
import com.example.myproject.exception.UserNotAuthenticatedException;
import com.example.myproject.service.FileUploadService;
import com.example.myproject.service.article.ArticleService;
import com.example.myproject.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final FileUploadService uploadService;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        return ResponseEntity.ok(articleService.findAllArticles());
    }


    @PostMapping(consumes = {"multipart/form-data"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Void> uploadArticle(@RequestParam String title,
                                              @RequestParam String body,
                                              @RequestParam(required = false) MultipartFile file,
                                              @AuthenticationPrincipal UserDetails userDetails,
                                              HttpServletResponse response) throws IOException {
        if(userDetails.getUsername() == null) {
            response.sendRedirect("/login");
        }
        String time = LocalDateTime.now().toString();
        uploadService.store(file, time);
        Article article = new Article(title, body, "img/"+ time + file.getOriginalFilename());
        articleService.saveArticle(article);
        User user = userService.findUserByEmail(userDetails.getUsername()).get();
        user.getArticles().add(article);
        userService.saveUser(user);
        response.sendRedirect("/");
        return ResponseEntity.ok().build();
    }
}
