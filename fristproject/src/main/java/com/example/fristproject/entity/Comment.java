package com.example.fristproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id  //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동으로 1식 증가
    private Long id;

    private String nickname;

    private String body;

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;  //  article_id bigint



}
