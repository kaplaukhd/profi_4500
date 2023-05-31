package com.example.myproject.repository;

import com.example.myproject.entity.Article;
import com.example.myproject.entity.dto.ArticleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT u.photoUrl AS avatarUrl," +
            "a.title," +
            " a.date," +
            " CONCAT(u.firstName, ' ', u.lastName) AS author," +
            " a.body," +
            " a.imageUrl FROM users u JOIN u.articles a order by a.date")

    List<Object[]> getAllArticleData();

    default List<ArticleDto> getAllArticles() {
        List<Object[]> articleDataList = getAllArticleData();
        List<ArticleDto> articles = new ArrayList<>();

        for (Object[] articleData : articleDataList) {
            ArticleDto articleDto = new ArticleDto() {
                @Override
                public String getAvatarUrl() {
                    return (String) articleData[0];
                }

                @Override
                public String getTitle() {
                    return (String) articleData[1];
                }

                @Override
                public LocalDateTime getDate() {
                    return (LocalDateTime) articleData[2];
                }

                @Override
                public String getAuthor() {
                    return (String) articleData[3];
                }

                @Override
                public String getBody() {
                    return (String) articleData[4];
                }

                @Override
                public String getImageUrl() {
                    return (String) articleData[5];
                }
            };

            articles.add(articleDto);
        }

        return articles;
    }

}
