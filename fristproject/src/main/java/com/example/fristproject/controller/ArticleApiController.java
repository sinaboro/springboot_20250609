package com.example.fristproject.controller;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.entity.Article;
import com.example.fristproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ArticleApiController {

    //@Autowired
    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public List<Article> index() {
        log.info(" restapi index");
        return articleRepository.findAll();
    }

    @GetMapping("/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @PostMapping("/articles")
    public Article create(@RequestBody ArticleForm form) {

        Article article = form.toEntity();

        return articleRepository.save(article);
    }

    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article>  update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        //1. DTO  -> entity 변환
        Article article = dto.toEntity();

        log.info(" restapi update {}", article);

        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리하기
        if (target == null || id != article.getId())  {
            log.info("id : {}, article: {}", id, article.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        log.info(" restapi update =====>  {}", article);

        //4. update 및 정상 응답하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article>  delete(@PathVariable Long id) {

        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //2. 잘못된 요청 처리하기
        if (target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        //3. 대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
