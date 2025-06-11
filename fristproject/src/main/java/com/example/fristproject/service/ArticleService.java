package com.example.fristproject.service;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.entity.Article;
import com.example.fristproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        log.info("1.................");
        articleRepository.findById(id).ifPresent(article -> {});
        log.info("2.................");
        Article article = articleRepository.findById(id).orElse(null);
        log.info("3.................");
        return article;
    }

    // create - insert sql 실행
    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        return articleRepository.save(article);
    }

    //update - update sql 실행
    public Article update(Long id, ArticleForm dto) {
        //1. DTO  -> entity 변환
        Article article = dto.toEntity();

        log.info(" restapi update {}", article);

        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리하기
        if (target == null || id != article.getId())  {
            log.info("id : {}, article: {}", id, article.getId());
            return null;
        }
        log.info(" restapi update =====>  {}", article);

        //4. update 및 정상 응답하기
        target.patch(article);
        return articleRepository.save(target);
    }


    public Article delete(Long id) {

        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //2. 잘못된 요청 처리하기
        if (target == null)
            return null;

        //3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }
}
