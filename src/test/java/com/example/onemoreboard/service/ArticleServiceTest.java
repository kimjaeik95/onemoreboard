package com.example.onemoreboard.service;

import com.example.onemoreboard.dto.ArticleRequest;
import com.example.onemoreboard.dto.ArticleResponse;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.onemoreboard.service
 * fileName       : ArticleServiceTest
 * author         : JAEIK
 * date           : 1/9/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/9/25       JAEIK       최초 생성
 */
@SpringBootTest
@ActiveProfiles("test")
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;
    @Test
    void findArticles() {
        /*
        1. 예상 데이터 작성
        2. 실제 데이터 획득
        3. 예상 실제 데이터 비교 및 검증
         */

        // Given
        List<Article> articleList = List.of(
                new Article(null,"제목1","오늘 너"),
                new Article(null,"제목2","너무"),
                new Article(null,"제목3","이쁘다")
        );
        articleRepository.saveAll(articleList);

        // When
        List<Article> articles = articleService.findArticles();

        // Then
        assertEquals(articleList.size(), articles.size(),"저장된 데이터와 조회된 데이터의 크기가 다릅니다.");
        for (int i = 0; i < articleList.size(); i++) {
            assertEquals(articleList.get(i).getTitle(), articles.get(i).getTitle(), "제목이 일치하지 않습니다.");
            assertEquals(articleList.get(i).getContent(), articles.get(i).getContent(), "내용이 일치하지 않습니다.");
        }
    }

    @Test
    void createArticle() {
        // Given
        Article article = new Article(null,"제목","취업하자");

        // When
        Article saveArticle = articleRepository.save(article);

        // Then
        assertNotNull(saveArticle.getId());
        assertEquals(article.toString(), saveArticle.toString());
        assertEquals(article.getTitle(), saveArticle.getTitle(), "제목이 일치하지 않습니다.");
    }

    @Test
    void restUpdateArticle() {
        // Given
        Article article = new Article(null, "취업", "취업하자!!");
        articleRepository.save(article);

        Long articleId = article.getId();
        ArticleRequest articleRequest = new ArticleRequest(null, "취업", "더 열심히 하자!!");

        // When
        ArticleResponse updateArticleResponse = articleService.restUpdateArticle(articleId, articleRequest);

        // Then
        assertNotNull(updateArticleResponse);
        assertEquals(articleRequest.getTitle(), updateArticleResponse.getTitle(), "제목이 일치하지 않습니다.");
        assertEquals(articleRequest.getContent(), updateArticleResponse.getContent(), "내용이 일치하지 않습니다.");
    }

    @Test
    void deleteArticleById() {
        // Given
        Article article = new Article(null,"취업","취업하자!");
        articleRepository.save(article);

        Long articleId = article.getId();

        // When
        articleRepository.deleteById(articleId);

        // Then
        assertTrue(articleRepository.findById(articleId).isEmpty());
    }
}