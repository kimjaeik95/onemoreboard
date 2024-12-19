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
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
