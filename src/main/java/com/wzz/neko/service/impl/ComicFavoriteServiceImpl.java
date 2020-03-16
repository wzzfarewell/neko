package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.ComicFavoriteMapper;
import com.wzz.neko.model.Comic;
import com.wzz.neko.model.ComicFavorite;
import com.wzz.neko.service.ComicFavoriteService;
import com.wzz.neko.service.ComicService;
import com.wzz.neko.service.UserService;
import com.wzz.neko.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * ComicFavoriteServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@Service
@Transactional
public class ComicFavoriteServiceImpl extends AbstractService<ComicFavorite> implements ComicFavoriteService {
    @Resource
    private ComicFavoriteMapper comicFavoriteMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ComicService comicService;

    @Override
    public int addFavoriteComic(String token, Long comicId) {
        ComicFavorite comicFavorite = new ComicFavorite();
        comicFavorite.setUserId(userService.getUserIdByToken(token));
        comicFavorite.setComicId(comicId);
        comicFavorite.setStatus(Const.BookmarkStatus.NORMAL);
        comicFavorite.setCreateTime(new Date());
        comicFavorite.setUpdateTime(new Date());
        return comicFavoriteMapper.insert(comicFavorite);
    }

    @Override
    public int deleteFavoriteComic(String token, Long comicId) {
        return comicFavoriteMapper.deleteByUserComicId(userService.getUserIdByToken(token), comicId);
    }

    @Override
    public List<Comic> listFavoriteComics(String token) {
        List<ComicFavorite> favorites = comicFavoriteMapper.listByUser(userService.getUserIdByToken(token));
        StringBuilder comicIds = new StringBuilder();
        for(ComicFavorite favorite : favorites){
            comicIds.append(favorite.getComicId()).append(",");
        }
        comicIds = new StringBuilder(comicIds.substring(0, comicIds.length() - 1));
        List<Comic> comics = comicService.findByIds(comicIds.toString());
        Iterator<Comic> it = comics.iterator();
        while(it.hasNext()){
            Comic c = it.next();
            c.setCover(ImgUtils.getShowPath(c.getCover()));
        }
        return comics;
    }
}
