package com.study.restdocs.domain.article.controller;

import com.study.restdocs.config.AbstractRestDocsTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
class ArticleControllerTest extends AbstractRestDocsTest {

    @Test
    void getAllArticles() throws Exception {
        mockMvc.perform(
                get("/articles")
                        .param("pageSize", "20")
                        .param("pageNumber", "0")
        ).andExpect(status().isOk())
                .andDo(
                        restDocs.document()
                );
    }

    @Test
    void getById() throws Exception{
        mockMvc.perform(
                get("/articles/" + 1)
        )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document()
                );
    }

    @Test
    void postArticle() throws Exception{
        HashMap<String, String> map = new HashMap<>();
        map.put("content", "콘텐츠임");
        mockMvc.perform(
                post("/articles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(map))
        ).andExpect(status().isOk())
                .andDo(restDocs.document());
    }

    @Test
    void testPostArticle() throws Exception{
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("content", "콘텐츠임");
        mockMvc.perform(
                post("/articles/update")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(map))
        ).andExpect(status().isOk())
                .andDo(restDocs.document());
    }
}