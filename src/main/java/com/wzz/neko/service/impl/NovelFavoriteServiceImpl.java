package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.NovelFavoriteMapper;
import com.wzz.neko.model.Novel;
import com.wzz.neko.model.NovelFavorite;
import com.wzz.neko.service.NovelFavoriteService;
import com.wzz.neko.service.NovelService;
import com.wzz.neko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * NovelFavoriteServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/10
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class NovelFavoriteServiceImpl extends AbstractService<NovelFavorite> implements NovelFavoriteService {
    @Resource
    private NovelFavoriteMapper novelFavoriteMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private NovelService novelService;

    @Override
    public int addFavoriteNovel(String token, Long novelId) {
        NovelFavorite novelFavorite = new NovelFavorite();
        novelFavorite.setUserId(userService.getUserIdByToken(token));
        novelFavorite.setBookId(novelId);
        novelFavorite.setStatus(Const.BookmarkStatus.NORMAL);
        novelFavorite.setCreateTime(new Date());
        novelFavorite.setUpdateTime(new Date());
        return novelFavoriteMapper.insert(novelFavorite);
    }

    @Override
    public int deleteFavoriteNovel(String token, Long novelId) {
        return novelFavoriteMapper.deleteByUserNovelId(userService.getUserIdByToken(token), novelId);
    }

    @Override
    public List<NovelFavorite> listFavoritesByUserId(Long userId) {
        return novelFavoriteMapper.listByUserId(userId);
    }

    @Override
    public List<Novel> listFavoriteNovels(String token) {
        List<NovelFavorite> favorites = this.listFavoritesByUserId(userService.getUserIdByToken(token));
        StringBuilder novelIds = new StringBuilder();
        for(NovelFavorite favorite : favorites){
            novelIds.append(favorite.getBookId()).append(",");
        }
        novelIds = new StringBuilder(novelIds.substring(0, novelIds.length() - 1));
        return novelService.findByIds(novelIds.toString());
    }

}
