package com.wzz.neko.model;

import javax.persistence.*;
import java.io.Serializable;

public class Chapter implements Serializable {
    private static final long serialVersionUID = 500539121247818027L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 章节对应的小说id
     */
    @Column(name = "book_id")
    private Long bookId;

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

    /**
     * 本章内容
     */
    private String content;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取章节对应的小说id
     *
     * @return book_id - 章节对应的小说id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 设置章节对应的小说id
     *
     * @param bookId 章节对应的小说id
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
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
     * 获取本章内容
     *
     * @return content - 本章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置本章内容
     *
     * @param content 本章内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}