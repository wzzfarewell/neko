package com.wzz.neko.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Comic implements Serializable {
    private static final long serialVersionUID = -2499733475866240468L;
    /**
     * 漫画表自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 漫画名
     */
    @Column(name = "comic_name")
    private String comicName;

    /**
     * 作者名
     */
    private String author;

    /**
     * 封面的链接
     */
    private String cover;

    /**
     * 漫画章节链接
     */
    @Column(name = "chapter_url")
    private String chapterUrl;

    /**
     * 漫画的类别：如武侠修真，都市言情
     */
    private String category;

    /**
     * 漫画最后的更新时间
     */
    @Column(name = "latest_update_time")
    private Date latestUpdateTime;

    /**
     * 数据库中最新章节序号
     */
    @Column(name = "latest_chapter_num")
    private Integer latestChapterNum;

    /**
     * 漫画的状态：0，连载中；1，完结；2，下架
     */
    private String status;

    /**
     * 这本漫画存储到我们数据库的时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 点击量
     */
    @Column(name = "click_count")
    private Long clickCount;

    /**
     * 漫画简介
     */
    private String description;

    /**
     * 获取漫画表自增主键
     *
     * @return id - 漫画表自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置漫画表自增主键
     *
     * @param id 漫画表自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取漫画名
     *
     * @return comic_name - 漫画名
     */
    public String getComicName() {
        return comicName;
    }

    /**
     * 设置漫画名
     *
     * @param comicName 漫画名
     */
    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    /**
     * 获取作者名
     *
     * @return author - 作者名
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者名
     *
     * @param author 作者名
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取封面的链接
     *
     * @return cover - 封面的链接
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置封面的链接
     *
     * @param cover 封面的链接
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 获取漫画章节链接
     *
     * @return chapter_url - 漫画章节链接
     */
    public String getChapterUrl() {
        return chapterUrl;
    }

    /**
     * 设置漫画章节链接
     *
     * @param chapterUrl 漫画章节链接
     */
    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    /**
     * 获取漫画的类别：如武侠修真，都市言情
     *
     * @return category - 漫画的类别：如武侠修真，都市言情
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置漫画的类别：如武侠修真，都市言情
     *
     * @param category 漫画的类别：如武侠修真，都市言情
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取漫画最后的更新时间
     *
     * @return latest_update_time - 漫画最后的更新时间
     */
    public Date getLatestUpdateTime() {
        return latestUpdateTime;
    }

    /**
     * 设置漫画最后的更新时间
     *
     * @param latestUpdateTime 漫画最后的更新时间
     */
    public void setLatestUpdateTime(Date latestUpdateTime) {
        this.latestUpdateTime = latestUpdateTime;
    }

    /**
     * 获取数据库中最新章节序号
     *
     * @return latest_chapter_num - 数据库中最新章节序号
     */
    public Integer getLatestChapterNum() {
        return latestChapterNum;
    }

    /**
     * 设置数据库中最新章节序号
     *
     * @param latestChapterNum 数据库中最新章节序号
     */
    public void setLatestChapterNum(Integer latestChapterNum) {
        this.latestChapterNum = latestChapterNum;
    }

    /**
     * 获取漫画的状态：0，连载中；1，完结；2，下架
     *
     * @return status - 漫画的状态：0，连载中；1，完结；2，下架
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置漫画的状态：0，连载中；1，完结；2，下架
     *
     * @param status 漫画的状态：0，连载中；1，完结；2，下架
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取这本漫画存储到我们数据库的时间
     *
     * @return add_time - 这本漫画存储到我们数据库的时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置这本漫画存储到我们数据库的时间
     *
     * @param addTime 这本漫画存储到我们数据库的时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取点击量
     *
     * @return click_count - 点击量
     */
    public Long getClickCount() {
        return clickCount;
    }

    /**
     * 设置点击量
     *
     * @param clickCount 点击量
     */
    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * 获取漫画简介
     *
     * @return description - 漫画简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置漫画简介
     *
     * @param description 漫画简介
     */
    public void setDescription(String description) {
        this.description = description;
    }
}