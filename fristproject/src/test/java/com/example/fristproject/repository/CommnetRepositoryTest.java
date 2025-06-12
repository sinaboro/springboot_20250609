package com.example.fristproject.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommnetRepositoryTest {

    @Autowired
    private CommnetRepository comment;

    @Test
    @DisplayName("특정 게시글 넥네임으로 조회")
    void findByArticleId() {

        comment.findByNickname("Kim")
                .forEach(System.out::println);

        //.forEach(System.out::println);
    }
}