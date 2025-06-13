package com.example.fristproject.entity;


import com.example.fristproject.dto.CommentDto;
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


    public static Comment cretae(CommentDto dto, Article article) {
        //예외 발생
        if(dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }

        if(dto.getArticleId() != article.getId() ){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }

        //엔티티 생성 및 반환
        return new Comment(dto.getId(), dto.getNickname(), dto.getBody(), article);
    }
}
