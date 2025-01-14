package com.example.onemoreboard.service;


import com.example.onemoreboard.dto.CommentRequest;
import com.example.onemoreboard.dto.CommentResponse;
import com.example.onemoreboard.entity.Article;
import com.example.onemoreboard.entity.Comment;
import com.example.onemoreboard.repository.ArticleRepository;
import com.example.onemoreboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.example.onemoreboard.service
 * fileName       : CommentService
 * author         : JAEIK
 * date           : 1/13/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/13/25       JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public List<CommentResponse> findComments(Long articleId) {
//        List<Comment> comments = commentRepository.findByArticleId(articleId);

//        List<CommentResponse> commentResponseList = new ArrayList<>();
//        for (int i =0; i < comments.size(); i++) {
//            Comment c = comments.get(i); // for (Comment c : comments) 리팩토링가능 / stream 방식가능
//            CommentResponse dto = CommentResponse.fromEntity(c);
//            commentResponseList.add(dto);
//        }
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentResponse.fromEntity(comment))
                .collect(Collectors.toList());

    }

    public CommentResponse saveComment(Long articleId, CommentRequest commentRequest) {
        /*
        1. 게시글 조회 예외
        2. 댓글 엔티티생성
        3. 댓글 엔티티 DB 저장
        4. Dto 변환해 반환
         */
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("대상 게시글이 없습니다."));

        Comment comment = Comment.createComment(commentRequest, article); // 엔티티 생성메서드사용 (엔티티가 자신의 생성을 책임지는 방식)
        //Comment comment = commentRequest.toEntity(article); // 디티오-엔티티 변환사용
        Comment saveComment = commentRepository.save(comment);

        return CommentResponse.fromEntity(saveComment);

    }

    @Transactional
    public CommentResponse updateByIdComment(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (commentRequest.getContent() == null) {
            throw new IllegalArgumentException("변경할 댓글내용이 없습니다.");
        }
        comment.updateComment(commentRequest.getContent());

        //commentRepository.save(comment); 생략가능 (변경감지로 영속상태 관리하여 업데이트 자동반영)

        return CommentResponse.fromEntity(comment);
    }

    public void deleteByIdComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }
}
