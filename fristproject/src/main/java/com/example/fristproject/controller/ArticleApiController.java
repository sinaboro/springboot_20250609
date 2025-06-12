package com.example.fristproject.controller;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.entity.Article;
import com.example.fristproject.repository.ArticleRepository;
import com.example.fristproject.service.ArticleService;
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
    //private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> index() {
        log.info(" restapi index");

        return articleService.index();
    }

    @GetMapping("/articles/{id}")
    public Article show(@PathVariable Long id) {

        return articleService.show(id);
    }

    @PostMapping("/articles")
    public ResponseEntity<Article>  create(@RequestBody ArticleForm dto) {

        Article created = articleService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article>  update(@PathVariable Long id, @RequestBody ArticleForm dto) {

        Article updated = articleService.update(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article>  delete(@PathVariable Long id) {

        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 연습
    @PostMapping("/transaction-test")
    public ResponseEntity<List<Article>>  transactionTest(@RequestBody List<ArticleForm> dtos) {

        log.info(" restapi transaction test");

       List<Article> createdList =  articleService.createArticles(dtos);

       return (createdList != null) ?
               ResponseEntity.status(HttpStatus.OK).body(createdList) :
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
