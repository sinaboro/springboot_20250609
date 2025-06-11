package com.example.fristproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //데이블로 생성
//@Table(name = "article") 데이블 이름
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //자동생성 기능 추가(숫자가 자동증가)
    private Long id;  //기본키

    @Column
    private String title;

    private String content;

    public void patch(Article article) {

        if(article.title != null){
            this.title = article.title;
        }

        if(article.content != null){
            this.content = article.content;
        }
    }
}
