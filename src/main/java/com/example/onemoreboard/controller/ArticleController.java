package com.example.onemoreboard.controller;

import com.example.onemoreboard.dto.ArticleRequest;
import com.example.onemoreboard.dto.ArticleResponse;
import com.example.onemoreboard.dto.CommentResponse;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.service.ArticleService;
import com.example.onemoreboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final CommentService commentService;
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
        List<CommentResponse> commentResponseList = commentService.findComments(id);
        model.addAttribute("article", article);
        model.addAttribute("comments", commentResponseList);
        return "articles/show";
    }

    @GetMapping("/finds")
    public String getArticles(Model model) {
        List<Article> articleList = articleService.findArticles();
        model.addAttribute("articles", articleList);
        return "articles/index";
    }

    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        ArticleResponse response = articleService.updateShowArticle(id);
        model.addAttribute("article",response);
        return "articles/edit";
    }

    @PostMapping("/update")
    public String updateEdit(ArticleRequest articleRequest) {
        articleService.updateArticle(articleRequest);
        return "redirect:/api/articles/find/" + articleRequest.getId();
    }

    // html post, get 메서드만 받을 수 있음
    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable("id")Long id, RedirectAttributes rttr) {
        articleService.deleteArticleById(id);
        rttr.addFlashAttribute("msg", "삭제됐습니다.");
        return "redirect:/api/articles/finds";
    }
}

