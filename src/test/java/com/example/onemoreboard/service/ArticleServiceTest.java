package com.example.onemoreboard.service;

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
        List<Article> articleList = List.of(
                new Article(null,"aaa","aaa"),
                new Article(null,"bbb","bbb"),
                new Article(null,"ccc","ccc")
        );
        articleRepository.saveAll(articleList);

        List<Article> articles = articleService.findArticles();

        assertEquals(articleList.size(), articles.size(),"저장된 데이터와 조회된 데이터의 크기가 다릅니다.");
        for (int i = 0; i < articleList.size(); i++) {
            assertEquals(articleList.get(i).getTitle(), articles.get(i).getTitle(), "제목이 일치하지 않습니다.");
            assertEquals(articleList.get(i).getContent(), articles.get(i).getContent(), "내용이 일치하지 않습니다.");
        }



    }
}