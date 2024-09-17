package com.example.usermanagement.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {
    private List<T> records;
    private long total;
    private int pageNum;
    private int pageSize;

    public PageInfo(List<T> records) {
        this.records = records;
    }
}
