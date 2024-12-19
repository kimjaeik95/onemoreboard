package com.example.onemoreboard.repository;

import com.example.onemoreboard.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.onemoreboard.repository
 * fileName       : ArticleRepository
 * author         : JAEIK
 * date           : 12/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/19/24       JAEIK       최초 생성
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
