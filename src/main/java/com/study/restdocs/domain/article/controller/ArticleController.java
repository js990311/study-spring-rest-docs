package com.study.restdocs.domain.article.controller;

import com.study.restdocs.domain.article.controller.form.ArticleCreateForm;
import com.study.restdocs.domain.article.controller.form.ArticleUpdateForm;
import com.study.restdocs.domain.article.dto.ArticleDto;
import com.study.restdocs.domain.article.service.ArticleService;
import com.study.restdocs.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public BasePageResponse<ArticleDto> getAllArticles(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize
    ){
        Page<ArticleDto> articles = articleService.readAllArticle(pageNumber, pageSize);
        return BasePageResponse.of(articles);
    }

    @GetMapping("/{id}")
    public ArticleDto getById(
            @PathVariable("id") long id
    ){
        return articleService.readById(id);
    }

    @PostMapping
    public ArticleDto postArticle(
            @RequestBody ArticleCreateForm form
    ){
        return articleService.createArticle(form.getContent());
    }

    @PostMapping("/update")
    public ArticleDto postArticle(
            @RequestBody ArticleUpdateForm form
    ){
        return articleService.updateArticle(form.getId(), form.getContent());
    }

}
