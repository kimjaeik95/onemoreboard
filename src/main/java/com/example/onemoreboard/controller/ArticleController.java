package com.example.onemoreboard.controller;

import com.example.onemoreboard.dto.ArticleRequest;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
public class ArticleController {

    private final ArticleService articleService;


    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute ArticleRequest articleRequest) {
        System.out.println("Title: " + articleRequest.getTitle());
        System.out.println("Content: " + articleRequest.getContent());
        articleService.createArticle(articleRequest);
        return "redirect:/articles/create";
    }

    @GetMapping("/articles/create")
    public String showCreateForm(Model model) {
        model.addAttribute("articleRequest", new ArticleRequest());
        return "articles/writing";
    }
}

