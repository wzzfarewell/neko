package com.wzz.neko.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "novel_cookies")
public class NovelCookies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    /**
     * 用户对本书浏览到的章节id
     */
    @Column(name = "chapter_id")
    private Long chapterId;

    /**
     * 1，此浏览记录正常；0，此浏览记录已被用户删除
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return book_id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取用户对本书浏览到的章节id
     *
     * @return chapter_id - 用户对本书浏览到的章节id
     */
    public Long getChapterId() {
        return chapterId;
    }

    /**
     * 设置用户对本书浏览到的章节id
     *
     * @param chapterId 用户对本书浏览到的章节id
     */
    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    /**
     * 获取1，此浏览记录正常；0，此浏览记录已被用户删除
     *
     * @return status - 1，此浏览记录正常；0，此浏览记录已被用户删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1，此浏览记录正常；0，此浏览记录已被用户删除
     *
     * @param status 1，此浏览记录正常；0，此浏览记录已被用户删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}