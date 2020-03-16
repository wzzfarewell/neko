package com.wzz.neko.dto;

import lombok.Data;

import java.util.Date;

/**
 * NovelCookiesVo
 *
 * @author wzzfarewell
 * @date 2020/3/11
 **/
@Data
public class NovelCookiesVo {
    private Long novelId;
    private String novelName;
    private Long chapterId;
    private String chapterName;
    private Date createTime;
}
