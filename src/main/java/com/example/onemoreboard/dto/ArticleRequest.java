package com.example.onemoreboard.dto;

import com.example.onemoreboard.entity.Article;
import lombok.*;

/**
 * packageName    : com.example.onemoreboard.dto
 * fileName       : ArticleRequest
 * author         : JAEIK
 * date           : 12/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/19/24       JAEIK       최초 생성
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class ArticleRequest {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .build();
    }

    public Article updateEntity(String title, String content) {
        return Article.builder()
                .id(this.id)
                .title(title)
                .content(content)
                .build();
    }
}
