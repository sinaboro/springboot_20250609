package com.example.fristproject.service;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.entity.Article;
import com.example.fristproject.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        if(article.getId() != null) {
            return null;
        }
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

    //@PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {

        //0.  toEntity(List<Article> articles) 변환
        ArticleForm articleForm = new ArticleForm();
        List<Article> articleList2 = articleForm.toEntity(dtos);

        //1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        //2. 엔티티 묶음을 DB에 저장하기
        articleList.stream().forEach(article -> {
                    articleRepository.save(article);
                }
        );

        entityManager.flush();
//        for(Article article : articleList) {
//            articleRepository.save(article);
//        }
        //3. 강제 예외 발생시키기
         Article article = articleRepository.findById(-1L)
                .orElseThrow(()->new IllegalArgumentException("결제 실패!!!"));
        //4. 결과 값 반환하기

        return articleList;
    }
}
