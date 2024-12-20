package com.study.restdocs.domain.index.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IndexsDto {
    private int size;
    private List<IndexDto> indexes;

    public IndexsDto(List<IndexDto> indexes) {
        this.size = indexes.size();
        this.indexes = indexes;
    }

    private static IndexsDto _indexes;
    public static IndexsDto getIndexs(){
        if(_indexes == null){
            List<IndexDto> indexes = new ArrayList<>();
            indexes.add(new IndexDto("/article", "article 관련"));
            _indexes = new IndexsDto(indexes);
        }
        return _indexes;
    }
}
