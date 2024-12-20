package com.study.restdocs.domain.article.dto;

import com.study.restdocs.domain.article.entity.Article;
import lombok.Getter;

@Getter
public class ArticleDto {
    private Long id;
    private String content;

    public ArticleDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static ArticleDto from(Article article){
        return new ArticleDto(article.getId(), article.getContent());
    }
}
