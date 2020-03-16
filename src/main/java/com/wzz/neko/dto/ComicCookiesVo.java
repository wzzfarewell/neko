package com.wzz.neko.dto;

import lombok.Data;

import java.util.Date;

/**
 * ComicCookiesVo
 *
 * @author wzzfarewell
 * @date 2020/3/14
 **/
@Data
public class ComicCookiesVo {
    private Long comicId;
    private String comicName;
    private Long chapterId;
    private String chapterName;
    private Date createTime;
}
