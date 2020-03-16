package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.model.Comic;
import com.wzz.neko.model.ComicFavorite;

import java.util.List;


/**
 * ComicFavoriteService
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
public interface ComicFavoriteService extends Service<ComicFavorite> {

    int addFavoriteComic(String token, Long comicId);

    int deleteFavoriteComic(String token, Long comicId);

    List<Comic> listFavoriteComics(String token);
}
