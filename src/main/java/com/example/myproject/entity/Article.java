package com.example.myproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;
    private String title;
    private LocalDateTime date = LocalDateTime.now();
    private String imageUrl;

    public Article(String body, String title,  String imageUrl) {
        this.body = body;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public Article(String body, String title) {
        this.body = body;
        this.title = title;
    }
}
