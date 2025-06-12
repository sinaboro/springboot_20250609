package com.example.fristproject.service;

import com.example.fristproject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {

        //1. 예상 데이터
        //2. 실제 데이터
        //3. 비교 및 검증
    }


    @Test
    void show_성공() {
        //id": 102,
        //"title": "시간 예약",
        //"content": "1111"

        //1. 예상 데이터
        Article expected = new Article(102L,"시간 예약", "1111" );

        //2. 실제 데이터
        Article article = articleService.show(102L);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

        log.info("article = {}", article);

    }

    @Test
    void show_실패() {
        //1. 예상 데이터
        Article expected = new Article(102L,"시간 예약", "2222" );

        //2. 실제 데이터
        Article article = articleService.show(102L);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

        log.info("article = {}", article);
    }


}