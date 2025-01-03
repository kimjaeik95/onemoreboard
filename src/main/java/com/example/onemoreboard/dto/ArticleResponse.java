package com.example.onemoreboard.dto;

import com.example.onemoreboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.example.onemoreboard.dto
 * fileName       : ArticleResponse
 * author         : JAEIK
 * date           : 12/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/20/24       JAEIK       최초 생성
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    public static ArticleResponse fromEntity(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
