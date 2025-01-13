package com.example.onemoreboard.restapicontroller;

import com.example.onemoreboard.dto.CommentResponse;
import com.example.onemoreboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.example.onemoreboard.restapicontroller
 * fileName       : CommentController
 * author         : JAEIK
 * date           : 1/13/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/13/25       JAEIK       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/{articleId}/comments")
    public ResponseEntity<?> getComments(@PathVariable("articleId") Long articleId) {
        List<CommentResponse> commentResponseList = commentService.findComments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseList);
    }
}