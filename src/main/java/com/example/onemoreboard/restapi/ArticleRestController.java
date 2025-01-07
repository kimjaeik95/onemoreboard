package com.example.onemoreboard.restapi;

import com.example.onemoreboard.dto.ArticleRequest;
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

    @PutMapping("/articles/update/{id}")
    public Article updateArticle(@PathVariable("id")Long id, @RequestBody ArticleRequest articleRequest) {
        return articleService.updateArticle(articleRequest);
    }

    @DeleteMapping("/articles/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id) {
         articleService.deleteArticleById(id);
         return ResponseEntity.noContent().build();

    }
}
