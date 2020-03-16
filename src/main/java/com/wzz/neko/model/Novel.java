package com.wzz.neko.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Novel implements Serializable {
    private static final long serialVersionUID = 4270197059845997164L;
    /**
     * 小说表自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 作者名
     */
    private String author;

    /**
     * 封面的链接
     */
    private String cover;

    /**
     * 小说章节链接
     */
    @Column(name = "chapter_url")
    private String chapterUrl;

    /**
     * 小说的类别：如武侠修真，都市言情
     */
    private String category;

    /**
     * 最后一章的章节名
     */
    @Column(name = "latest_chapter_name")
    private String latestChapterName;

    /**
     * 最后一章的url
     */
    @Column(name = "latest_chapter_url")
    private String latestChapterUrl;

    /**
     * 小说最后的更新时间
     */
    @Column(name = "latest_update_time")
    private Date latestUpdateTime;

    /**
     * 小说的状态：0，连载中；1，完结；2，下架
     */
    private String status;

    /**
     * 这本小说存储到我们数据库的时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 小说简介
     */
    private String description;

    @Column(name = "click_count")
    private Long clickCount;

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * 获取小说表自增主键
     *
     * @return id - 小说表自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置小说表自增主键
     *
     * @param id 小说表自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取书名
     *
     * @return book_name - 书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置书名
     *
     * @param bookName 书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
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
     * 获取小说章节链接
     *
     * @return chapter_url - 小说章节链接
     */
    public String getChapterUrl() {
        return chapterUrl;
    }

    /**
     * 设置小说章节链接
     *
     * @param chapterUrl 小说章节链接
     */
    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    /**
     * 获取小说的类别：如武侠修真，都市言情
     *
     * @return category - 小说的类别：如武侠修真，都市言情
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置小说的类别：如武侠修真，都市言情
     *
     * @param category 小说的类别：如武侠修真，都市言情
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取最后一章的章节名
     *
     * @return latest_chapter_name - 最后一章的章节名
     */
    public String getLatestChapterName() {
        return latestChapterName;
    }

    /**
     * 设置最后一章的章节名
     *
     * @param latestChapterName 最后一章的章节名
     */
    public void setLatestChapterName(String latestChapterName) {
        this.latestChapterName = latestChapterName;
    }

    /**
     * 获取最后一章的url
     *
     * @return latest_chapter_url - 最后一章的url
     */
    public String getLatestChapterUrl() {
        return latestChapterUrl;
    }

    /**
     * 设置最后一章的url
     *
     * @param latestChapterUrl 最后一章的url
     */
    public void setLatestChapterUrl(String latestChapterUrl) {
        this.latestChapterUrl = latestChapterUrl;
    }

    /**
     * 获取小说最后的更新时间
     *
     * @return latest_update_time - 小说最后的更新时间
     */
    public Date getLatestUpdateTime() {
        return latestUpdateTime;
    }

    /**
     * 设置小说最后的更新时间
     *
     * @param latestUpdateTime 小说最后的更新时间
     */
    public void setLatestUpdateTime(Date latestUpdateTime) {
        this.latestUpdateTime = latestUpdateTime;
    }

    /**
     * 获取小说的状态：0，连载中；1，完结；2，下架
     *
     * @return status - 小说的状态：0，连载中；1，完结；2，下架
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置小说的状态：0，连载中；1，完结；2，下架
     *
     * @param status 小说的状态：0，连载中；1，完结；2，下架
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取这本小说存储到我们数据库的时间
     *
     * @return add_time - 这本小说存储到我们数据库的时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置这本小说存储到我们数据库的时间
     *
     * @param addTime 这本小说存储到我们数据库的时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取小说简介
     *
     * @return description - 小说简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置小说简介
     *
     * @param description 小说简介
     */
    public void setDescription(String description) {
        this.description = description;
    }
}