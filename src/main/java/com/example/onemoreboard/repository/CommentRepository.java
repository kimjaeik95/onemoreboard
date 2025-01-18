package com.example.onemoreboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.onemoreboard.entity.Comment;

/**
 * packageName    : com.example.onemoreboard.repository
 * fileName       : CommentRepository
 * author         : JAEIK
 * date           : 1/10/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/10/25       JAEIK       최초 생성
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM board.comment WHERE article_id = :articleId ORDER BY id ASC", nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    @Query(value = "SELECT * FROM board.comment WHERE nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(@Param("nickname") String nickname);

}



