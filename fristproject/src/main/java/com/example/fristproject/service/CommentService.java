package com.example.fristproject.service;

import com.example.fristproject.dto.CommentDto;
import com.example.fristproject.entity.Article;
import com.example.fristproject.entity.Comment;
import com.example.fristproject.repository.ArticleRepository;
import com.example.fristproject.repository.CommnetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public CommentDto update(Long id, CommentDto dto) {
        
        //1. 댓글 조회 및 예외 발생
        Comment target = commnetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!, 대상 댓글이 없습니다!! "));

        //2. 댓글 수정
        target.patch(dto);

        //3. DB에 갱신
        Comment updated = commnetRepository.save(target);

        //4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {

        //1. 댓글 조회 및 예외 발생
         Comment target = commnetRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

         String aLong = target.getArticle().getTitle();
         log.info("aLong >>>>>>>>>>>>>>>= {}", aLong);
         //2. 댓글 삭제
         commnetRepository.delete(target);

        //3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}













