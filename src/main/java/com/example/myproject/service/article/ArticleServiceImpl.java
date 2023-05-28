package com.example.myproject.service.article;

import com.example.myproject.entity.Article;
import com.example.myproject.entity.dto.ArticleDto;
import com.example.myproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    @Override
    public List<ArticleDto> findAllArticles() {
        return articleRepository.getAllArticles();
    }

    @Override
    public void removeArticleById(Long id) {
     articleRepository.deleteById(id);
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }
}
