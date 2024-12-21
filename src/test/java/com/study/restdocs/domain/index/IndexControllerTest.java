package com.study.restdocs.domain.index;

import com.study.restdocs.config.AbstractRestDocsTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
class IndexControllerTest extends AbstractRestDocsTest {

    @Test
    void indexes() throws Exception {
        mockMvc.perform(
                get("/")
        ).andExpect(status().isOk())
                .andDo(
                        restDocs.document()
                )
                .andDo(MockMvcRestDocumentationWrapper.document(
                        "get-indexes",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .summary("서버 내 indexes를 모두 가져옴")
                                        .responseFields(
                                            fieldWithPath("size").description("indexes의 사이즈"),
                                            fieldWithPath("indexes").description("indexes의 목록"),
                                            fieldWithPath("indexes[].path").description("index 항목의 경로"),
                                            fieldWithPath("indexes[].description").description("index 항목의 설명")
                                        )
                                        .build()
                        )
                ));
    }
}