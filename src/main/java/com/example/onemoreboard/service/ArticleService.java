package com.example.onemoreboard.service;

import com.example.onemoreboard.dto.ArticleRequest;
import com.example.onemoreboard.dto.ArticleResponse;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.example.onemoreboard.service
 * fileName       : ArticleService
 * author         : JAEIK
 * date           : 12/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/19/24       JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article createArticle(ArticleRequest articleRequest) {
        Article article = articleRequest.toEntity();
        articleRepository.save(article);
        return article;
    }

    public Article findArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }


    public ArticleResponse updateShowArticle(Long id) {
      Article article = articleRepository.findById(id).orElse(null);
      return ArticleResponse.fromEntity(article);
    }


    public void updateArticle(ArticleRequest articleRequest) {
        Article existingArticle = articleRepository.findById(articleRequest.getId()).orElse(null);
        Article article = articleRequest.updateEntity(articleRequest.getTitle(), articleRequest.getContent());
        if (existingArticle != null) {
            articleRepository.save(article);
        }
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }
}
