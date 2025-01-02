package com.example.onemoreboard.controller;

import com.example.onemoreboard.dto.ArticleRequest;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * packageName    : com.example.onemoreboard.controller
 * fileName       : ArticleControoler
 * author         : JAEIK
 * date           : 12/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/19/24       JAEIK       최초 생성
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/articles")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("articleRequest", new ArticleRequest());
        return "articles/writing";
    }

    @PostMapping("/create")
    public String createArticle(@ModelAttribute ArticleRequest articleRequest) {
        log.info(articleRequest.getTitle());
        log.info(articleRequest.getContent());
        Article article = articleService.createArticle(articleRequest);
        return "redirect:/api/articles/find/" + article.getId();
    }

    @GetMapping("/find/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "articles/show";
    }

    @GetMapping("/finds")
    public String getArticles(Model model) {
        List<Article> articleList = articleService.findArticles();
        model.addAttribute("articles", articleList);
        return "articles/index";
    }
}

