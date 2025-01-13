package com.example.onemoreboard.restapicontroller;

import com.example.onemoreboard.dto.ArticleRequest;
import com.example.onemoreboard.dto.ArticleResponse;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.example.onemoreboard.restapi
 * fileName       : ArticleRestController
 * author         : JAEIK
 * date           : 1/7/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/7/25       JAEIK       최초 생성
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleRestController {
    private final ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> getArticles() {
       return articleService.findArticles();
    }

    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable("id") Long id) {
        return articleService.findArticleById(id);
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody ArticleRequest articleRequest) {
        return articleService.createArticle(articleRequest);
    }

    @PatchMapping("/articles/update/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id")Long id, @RequestBody ArticleRequest articleRequest) {
         ArticleResponse articleResponse = articleService.restUpdateArticle(id, articleRequest);
         return ResponseEntity.ok().body(articleResponse);
    }

    @DeleteMapping("/articles/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id) {
         articleService.deleteArticleById(id);
         return ResponseEntity.noContent().build();

    }

    @PostMapping("/articles/test")
    public ResponseEntity<?> transactionTest(@RequestBody List<ArticleRequest> articleRequestList) throws IllegalAccessException {
        articleService.test(articleRequestList);
        return ResponseEntity.ok().build();
    }
}
