package com.study.restdocs.domain.index;

import com.study.restdocs.domain.index.dto.IndexsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping
    public IndexsDto getIndex(){
        return IndexsDto.getIndexs();
    }
}
