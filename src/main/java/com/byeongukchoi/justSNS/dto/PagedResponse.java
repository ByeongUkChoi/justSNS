package com.byeongukchoi.justSNS.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PagedResponse<T> {
    private List<T> item;
    private Page page;

    public PagedResponse(List<T> item, int pageNumber, int pageSize, long totalElements) {
        this.item = item;
        this.page = new Page(pageNumber, pageSize, totalElements);
    }
}
