package com.example.onemoreboard.service;

import com.example.onemoreboard.dto.ArticleRequest;
import com.example.onemoreboard.dto.ArticleResponse;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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


    public Article updateArticle(ArticleRequest articleRequest) {
        Article existingArticle = articleRepository.findById(articleRequest.getId()).orElse(null);
        Article article = articleRequest.updateEntity(articleRequest.getTitle(), articleRequest.getContent());
        if (existingArticle != null) {
            articleRepository.save(article);
        }
        return existingArticle;
    }

    public void deleteArticleById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);

        if (article != null) {
            articleRepository.deleteById(id);
        }
        throw new RuntimeException("게시글이 없습니다.");
    }

    // 타임리프 업데이트방식이랑 RestApi 업데이트방식이 다르다.
    public ArticleResponse restUpdateArticle(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id).orElse(null);

        if (article != null) {
           article.updateArticle(articleRequest.getTitle(),articleRequest.getContent());
           articleRepository.save(article);
           return ArticleResponse.fromEntity(article);
        }
        return null;
    }

    // 트랜잭션 테스트
    @Transactional
    public List<Article> test(List<ArticleRequest> articleRequestList) throws IllegalAccessException {
        List<Article> article = articleRequestList.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        article.stream()
                .forEach(articles -> articleRepository.save(articles));

        articleRepository.findById(-1L)
                .orElseThrow(()-> new RuntimeException("결제실패"));

        return article;
    }
}


