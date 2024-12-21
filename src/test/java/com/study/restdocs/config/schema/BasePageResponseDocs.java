package com.study.restdocs.config.schema;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class BasePageResponseDocs {
    private static FieldDescriptor[] _docs = new FieldDescriptor[]{
            fieldWithPath("size").description("content의 크기"),
            fieldWithPath("content").description("실제 데이터들"),
            fieldWithPath("page").description("현재 페이지의 번호"),
            fieldWithPath("pageSize").description("전체 페이지의 크기")
    };

    public static FieldDescriptor[] of(FieldDescriptor[] content){
        List<FieldDescriptor> ret = new ArrayList<>();
        Collections.addAll(ret, _docs);
        Collections.addAll(ret, content);
        return ret.toArray(FieldDescriptor[]::new);
    }

}
