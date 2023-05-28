package com.example.myproject.service.article;

import com.example.myproject.entity.Article;
import com.example.myproject.entity.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<ArticleDto> findAllArticles();

    void removeArticleById(Long id);

    void saveArticle(Article article);

    Optional<Article> findArticleById(Long id);
}
