package com.example.onemoreboard.entity;

import com.example.onemoreboard.dto.CommentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * packageName    : com.example.onemoreboard.entity
 * fileName       : Comment
 * author         : JAEIK
 * date           : 1/10/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/10/25       JAEIK       최초 생성
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "board", name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    private String nickname;
    private String content;
    private Instant createAt;

    public Comment(Long id, Article article, String nickname, String content) {
        this.id = id;
        this.article = article;
        this.nickname = nickname;
        this.content = content;
        this.createAt = Instant.now();
    }

    public Comment(Article article, String nickname, String content) {
        this.article = article;
        this.nickname = nickname;
        this.content = content;
        this.createAt = Instant.now();
    }

    public void updateComment(String content) {
        this.content = content;
    }

    // 생성자 방식
    public static Comment createComment(CommentRequest commentRequest, Article article) {
        return new Comment(
                article,
                commentRequest.getNickname(),
                commentRequest.getContent()
        );
    }
    // 빌더방식
//    public static Comment create(CommentRequest commentRequest, Article article) {
//        return Comment.builder()
//                .article(article)
//                .nickname(commentRequest.getNickname())
//                .content(commentRequest.getContent())
//                .build();
//    }
}
