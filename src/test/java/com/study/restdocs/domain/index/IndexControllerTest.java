package com.study.restdocs.domain.index;

import com.study.restdocs.config.AbstractRestDocsTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndexControllerTest extends AbstractRestDocsTest {

    @Test
    void indexes() throws Exception {
        mockMvc.perform(
                get("/")
        ).andExpect(status().isOk())
                .andDo(
                        restDocs.document()
                );
    }
}