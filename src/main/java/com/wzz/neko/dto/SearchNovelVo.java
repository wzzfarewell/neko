package com.wzz.neko.dto;

import lombok.Data;

/**
 * SearchNovelVo
 *
 * @author wzzfarewell
 * @date 2020/3/7
 **/
@Data
public class SearchNovelVo {
    /**
     * 搜索小说时所选的分类
     */
    private String categoryName;
    /**
     * 搜索小说时所选的排序方式
     */
    private String sort;
    /**
     * 搜索小说时所选的小说状态
     */
    private String status;

    private Integer pageNum;

    private Integer pageSize;

    private String nameOrAuthor;
}
