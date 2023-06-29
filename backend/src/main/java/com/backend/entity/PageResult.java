package com.backend.entity;

import lombok.Data;

import java.util.List;
@Data
public class PageResult {
    private int totalCount;// 总记录数
    private List<?> list;    // 列表数据
    public PageResult(List<?> list,int totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }
}
