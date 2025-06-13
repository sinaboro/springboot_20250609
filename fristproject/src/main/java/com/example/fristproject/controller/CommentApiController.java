package com.example.fristproject.controller;

import com.example.fristproject.dto.CommentDto;
import com.example.fristproject.entity.Comment;
import com.example.fristproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
    
    //1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {

        List<CommentDto> dtos = commentService.comments(articleId);

        return null;
    }

    //2. 댓글 생성
    
    //3. 댓글 수정
    
    //4. 댓글 삭제
}
