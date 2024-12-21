package com.study.restdocs.domain.article.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.study.restdocs.config.AbstractRestDocsTest;
import com.study.restdocs.config.schema.BasePageResponseDocs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
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
                )
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "get-all-articles",
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("articles")
                                                .summary("Article을 모두 보기")
                                                .queryParameters(
                                                        parameterWithName("pageSize").description("페이지가 포함하는 데이터의 크기"),
                                                        parameterWithName("pageNumber").description("가져오려는 페이지의 번호")
                                                )
                                                .responseFields(
                                                        BasePageResponseDocs.of(
                                                            ArticleDtoDocs.docs("content[].")
                                                        )
                                                )
                                                .build()
                                )
                        )
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

    static class ArticleDtoDocs{
        public static FieldDescriptor[] docs(String prefix){
            return new FieldDescriptor[]{
                    fieldWithPath(prefix + "id").description("article의 고유번호"),
                    fieldWithPath(prefix + "content").description("article의 콘텐츠")
            };
        }
    }
}