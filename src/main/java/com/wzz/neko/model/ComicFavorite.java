package com.wzz.neko.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "comic_favorite")
public class ComicFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comic_id")
    private Long comicId;

    /**
     * 1,此漫画在用户的收藏夹中；0， 此漫画被用户从收藏夹中删除
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
     * @return comic_id
     */
    public Long getComicId() {
        return comicId;
    }

    /**
     * @param comicId
     */
    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    /**
     * 获取1,此漫画在用户的收藏夹中；0， 此漫画被用户从收藏夹中删除
     *
     * @return status - 1,此漫画在用户的收藏夹中；0， 此漫画被用户从收藏夹中删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1,此漫画在用户的收藏夹中；0， 此漫画被用户从收藏夹中删除
     *
     * @param status 1,此漫画在用户的收藏夹中；0， 此漫画被用户从收藏夹中删除
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