package com.example.onemoreboard.repository;

import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.onemoreboard.repository
 * fileName       : CommentRepositoryTest
 * author         : JAEIK
 * date           : 1/10/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/10/25       JAEIK       최초 생성
 */
@DataJpaTest
@ActiveProfiles("test")
class CommentRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Test
    @DisplayName("특정게시글의 모든 댓글 조회")
    void findByArticleId() {
        Article article = new Article(null,"나 좋아하는사람","댓글ㄱㄱ");
        articleRepository.save(article);
        System.out.println("article Id" + article.getId());
        Comment comment = new Comment(null,article,"김재익","나 너 좋아해", Instant.now());
        Comment comment2 = new Comment(null,article,"이새봄","난 너 싫어해", Instant.now());
        commentRepository.save(comment);
        commentRepository.save(comment2);

        Long articleId = article.getId();
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        assertNotNull(comments);
        assertEquals(2, comments.size(),"댓글 수가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("닉네임으로 모든 댓글 조회")
    void findByNickname() {
        /*
        1. 입력 데이터
        2. 실제 데이터
        3. 예상 데이터
        4. 비교 및 검증
         */
        Article article = new Article(null,"나 좋아하는사람","댓글ㄱㄱ");
        articleRepository.save(article);

        Comment comment = new Comment(null,article,"김재익","나 너 좋아해", Instant.now());
        Comment comment2 = new Comment(null,article,"이새봄","난 너 싫어해", Instant.now());
        commentRepository.save(comment);
        commentRepository.save(comment2);

        String nickname = "김재익";

        List<Comment> comments = commentRepository.findByNickname(nickname);

        assertEquals(1, comments.size(), "댓글 수가 일치하지 않습니다");
    }
}