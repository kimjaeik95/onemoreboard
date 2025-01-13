package com.example.onemoreboard.dto;

import com.example.onemoreboard.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * packageName    : com.example.onemoreboard.dto
 * fileName       : CommentResponse
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
public class CommentResponse {
    private Long id;
    private Long articleId;
    private String nickname;
    private String content;
    private Instant createAt;

    // 빌더 방식
    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .articleId(comment.getArticle().getId())
                .nickname(comment.getContent())
                .content(comment.getContent())
                .createAt(comment.getCreateAt())
                .build();
    }
}
    // 생성자 방식
//    public static CommentResponse fromEntity2(Comment comment) {
//        return new CommentResponse(
//                comment.getId(),
//                comment.getArticle().getId(),
//                comment.getNickname(),
//                comment.getContent(),
//                comment.getCreateAt()
//        );
//    }

