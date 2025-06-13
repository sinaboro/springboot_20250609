package com.example.fristproject.service;

import com.example.fristproject.dto.CommentDto;
import com.example.fristproject.entity.Article;
import com.example.fristproject.entity.Comment;
import com.example.fristproject.repository.ArticleRepository;
import com.example.fristproject.repository.CommnetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommnetRepository commnetRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {

       /*
        //1. 댓글 조회
        List<Comment> comments =
                commnetRepository.findByArticleId(articleId);

        //2. 엔티티 -> DTO 변환
         List<CommentDto> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto dto =
                    CommentDto.createCommentDto(comment);
            dtos.add(dto);
        }

        //3. 결과 반환
        return dtos;
        */

        List<CommentDto> list = commnetRepository.
                findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

        return list;
    }

    public CommentDto create(Long articleId, CommentDto dto) {
        //1. 게시글 조회 및 예외 발생  , "댓글 생성 실패, 5번 게시글은 없습니다!",
        Article article = articleRepository.findById(articleId).
                orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패, " + articleId + "번 게시글은 없습니다!"));

        //2. 댓글 엔티티 생성
         Comment comment = Comment.cretae(dto, article);

        //3. 댓글 엔티티를 DB에 저장
         Comment created = commnetRepository.save(comment);

        //4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
}













