package com.byeongukchoi.justSNS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Page {
    private int number;
    private int size;
    private long totalElements;
}
