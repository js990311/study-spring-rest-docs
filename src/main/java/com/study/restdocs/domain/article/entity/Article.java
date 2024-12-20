package com.study.restdocs.domain.article.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column
    private String content;

    public void updateContent(String content){
        this.content = content;
    }

    public Article(String content) {
        this.content = content;
    }
}
