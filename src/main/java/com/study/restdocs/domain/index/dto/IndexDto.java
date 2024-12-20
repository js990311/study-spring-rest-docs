package com.study.restdocs.domain.index.dto;

import lombok.Getter;

@Getter
public class IndexDto {
    private final String path;
    private final String description;

    public IndexDto(String path, String description) {
        this.path = path;
        this.description = description;
    }
}
