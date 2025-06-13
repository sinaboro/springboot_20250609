package com.example.fristproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {

    private Long id; //댓글 id
    private Long articleId;  //부모 댓글

    private String nickname;
    private String body;
}
