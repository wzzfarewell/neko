package com.wzz.neko.model;

import javax.persistence.*;

@Table(name = "comic_chapter_content")
public class ComicChapterContent {
    /**
     * 漫画章节内容表自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 漫画章节id
     */
    @Column(name = "chapter_id")
    private Long chapterId;

    /**
     * 章节内容序号，即这一章的第几页
     */
    @Column(name = "content_num")
    private Integer contentNum;

    /**
     * 这一页的存储路径
     */
    private String path;

    /**
     * 获取漫画章节内容表自增主键
     *
     * @return id - 漫画章节内容表自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置漫画章节内容表自增主键
     *
     * @param id 漫画章节内容表自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取漫画章节id
     *
     * @return chapter_id - 漫画章节id
     */
    public Long getChapterId() {
        return chapterId;
    }

    /**
     * 设置漫画章节id
     *
     * @param chapterId 漫画章节id
     */
    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    /**
     * 获取章节内容序号，即这一章的第几页
     *
     * @return content_num - 章节内容序号，即这一章的第几页
     */
    public Integer getContentNum() {
        return contentNum;
    }

    /**
     * 设置章节内容序号，即这一章的第几页
     *
     * @param contentNum 章节内容序号，即这一章的第几页
     */
    public void setContentNum(Integer contentNum) {
        this.contentNum = contentNum;
    }

    /**
     * 获取这一页的存储路径
     *
     * @return path - 这一页的存储路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置这一页的存储路径
     *
     * @param path 这一页的存储路径
     */
    public void setPath(String path) {
        this.path = path;
    }
}