package com.wzz.neko.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "comic_chapter")
public class ComicChapter implements Serializable {
    private static final long serialVersionUID = 6088980183071776241L;
    /**
     * 漫画章节表自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 漫画id
     */
    @Column(name = "comic_id")
    private Long comicId;

    /**
     * 章节序号
     */
    @Column(name = "chapter_num")
    private Integer chapterNum;

    /**
     * 章节名
     */
    @Column(name = "chapter_name")
    private String chapterName;

    @Column(name = "chapter_url")
    private String chapterUrl;

    /**
     * 获取漫画章节表自增主键
     *
     * @return id - 漫画章节表自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置漫画章节表自增主键
     *
     * @param id 漫画章节表自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取漫画id
     *
     * @return comic_id - 漫画id
     */
    public Long getComicId() {
        return comicId;
    }

    /**
     * 设置漫画id
     *
     * @param comicId 漫画id
     */
    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    /**
     * 获取章节序号
     *
     * @return chapter_num - 章节序号
     */
    public Integer getChapterNum() {
        return chapterNum;
    }

    /**
     * 设置章节序号
     *
     * @param chapterNum 章节序号
     */
    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    /**
     * 获取章节名
     *
     * @return chapter_name - 章节名
     */
    public String getChapterName() {
        return chapterName;
    }

    /**
     * 设置章节名
     *
     * @param chapterName 章节名
     */
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    /**
     * @return chapter_url
     */
    public String getChapterUrl() {
        return chapterUrl;
    }

    /**
     * @param chapterUrl
     */
    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }
}