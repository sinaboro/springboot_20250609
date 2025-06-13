package com.example.fristproject.service;

import com.example.fristproject.dto.CommentDto;
import com.example.fristproject.entity.Comment;
import com.example.fristproject.repository.ArticleRepository;
import com.example.fristproject.repository.CommnetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommnetRepository commnetRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        
        //1. 댓글 조회
        List<Comment> comments =
                commnetRepository.findByArticleId(articleId);

        //2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<>();
        for (CommentDto comment : dtos) {

        }
        //3. 결과 반환

        return null;
    }
}
