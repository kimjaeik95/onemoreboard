package com.example.onemoreboard.dto;

import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * packageName    : com.example.onemoreboard.dto
 * fileName       : CommentRequest
 * author         : JAEIK
 * date           : 1/13/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/13/25       JAEIK       최초 생성
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentRequest {
    private Long articleId;
    private String nickname;
    private String content;
    private Instant createAt;

    public Comment toEntity(Article article) {
        return Comment.builder()
                .article(article)
                .nickname(this.nickname)
                .content(this.content)
                .createAt(Instant.now())
                .build();
    }
}

