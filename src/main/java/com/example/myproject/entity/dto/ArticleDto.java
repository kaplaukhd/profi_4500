package com.example.myproject.entity.dto;

import java.time.LocalDateTime;

public interface ArticleDto {
    String getAvatarUrl();

    String getTitle();
    LocalDateTime getDate();
    String getAuthor();
    String getBody();
    String getImageUrl();

}
