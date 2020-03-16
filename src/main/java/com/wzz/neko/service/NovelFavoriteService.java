package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.model.Novel;
import com.wzz.neko.model.NovelFavorite;

import java.util.List;


/**
 * NovelFavoriteService
 *
 * @author wzzfarewell
 * @date 2020/03/10
 **/
public interface NovelFavoriteService extends Service<NovelFavorite> {
    /**
     * 根据前端传入的登录状态token和小说id添加小说收藏夹记录
     * @param token 登录状态token
     * @param novelId 小说id
     * @return sql执行的行数
     */
    int addFavoriteNovel(String token, Long novelId);
    /**
     * 根据前端传入的登录状态token和小说id删除小说收藏夹记录，即将该记录`status`字段置0
     * @param token 登录状态token
     * @param novelId 小说id
     * @return sql执行的行数
     */
    int deleteFavoriteNovel(String token, Long novelId);

    List<NovelFavorite>  listFavoritesByUserId(Long userId);

    List<Novel> listFavoriteNovels(String token);
}
