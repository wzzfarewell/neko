package com.wzz.neko.dto;

import lombok.Data;

import java.util.Date;

/**
 * BookmarkVo
 *
 * @author wzzfarewell
 * @date 2020/3/11
 **/
@Data
public class BookmarkVo {
    private Long novelId;
    private Long chapterId;
    private String chapterName;
    private Date createTime;
}
