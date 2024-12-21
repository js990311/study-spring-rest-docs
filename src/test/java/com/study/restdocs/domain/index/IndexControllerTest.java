package com.study.restdocs.domain.index;

import com.study.restdocs.config.AbstractRestDocsTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                                                Indexes.all()
                                        )
                                        .build()
                        )
                ));
    }

    static class Indexes{
        public static FieldDescriptor[] container(){
            return new FieldDescriptor[]{
                    fieldWithPath("size").description("indexes의 사이즈"),
                    fieldWithPath("indexes").description("indexes의 목록")
            };
        }

        public static FieldDescriptor[] body(String prefix){
            return new FieldDescriptor[]{
                    fieldWithPath(prefix + "path").description("index 항목의 경로"),
                    fieldWithPath(prefix + "description").description("index 항목의 설명")
            };
        }

        public static FieldDescriptor[] all(){
            List<FieldDescriptor> ret = new ArrayList<>();
            Collections.addAll(ret, container());
            Collections.addAll(ret, body("indexes[]."));
            return ret.toArray(FieldDescriptor[]::new);
        }
    }

}