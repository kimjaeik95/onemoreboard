package com.example.onemoreboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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
}
