package com.study.restdocs.domain.article.service;

import com.study.restdocs.domain.article.dto.ArticleDto;
import com.study.restdocs.domain.article.entity.Article;
import com.study.restdocs.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    /* Create */
    @Transactional
    public ArticleDto createArticle(String content){
        Article article = new Article(content);
        Article save = articleRepository.save(article);
        return ArticleDto.from(save);
    }

    /* read */
    public Page<ArticleDto> readAllArticle(int pageNumber, int pageSize){
        return articleRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(ArticleDto::from);
    }

    public ArticleDto readById(long id){
        return ArticleDto.from(articleRepository.findById(id).orElseThrow());
    }

    /* Update */
    @Transactional
    public ArticleDto updateArticle(Long id, String content){
        Article article = articleRepository.findById(id).orElseThrow();
        article.updateContent(content);
        return ArticleDto.from(article);
    }
}
