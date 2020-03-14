package com.yiquanxinhe.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Raw
 * @Date: 2020/3/14 14:50
 * @Description: 分页信息
 */
@Setter
@Getter
@ToString
public class PageQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 8;

    private String keyword;


    private Integer offset;

    public Integer getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }
}
