package com.example.onemoreboard.service;


import com.example.onemoreboard.dto.CommentResponse;
import com.example.onemoreboard.repository.ArticleRepository;
import com.example.onemoreboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
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
        return  commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentResponse.fromEntity(comment))
                .collect(Collectors.toList());

    }
}
