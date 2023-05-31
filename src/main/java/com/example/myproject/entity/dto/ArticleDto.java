package com.example.myproject.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface ArticleDto {
    String getAvatarUrl();

    String getTitle();

    @JsonFormat(pattern = "dd/MM/YYYY")
    LocalDateTime getDate();
    String getAuthor();
    String getBody();
    String getImageUrl();

}
