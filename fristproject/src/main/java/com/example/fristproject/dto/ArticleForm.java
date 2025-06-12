package com.example.fristproject.dto;

import com.example.fristproject.entity.Article;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {

        return new Article(id, title, content);
    }

    public List<Article>  toEntity(List<ArticleForm> articles) {

        List list = new ArrayList();

        for(ArticleForm article : articles) {
            list.add( new Article(id, title, content));
        }
        return list;
    }
}
