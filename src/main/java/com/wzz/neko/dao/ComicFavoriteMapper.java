package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.ComicFavorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComicFavoriteMapper extends Mapper<ComicFavorite> {
    int deleteByUserComicId(@Param("userIdByToken") Long userIdByToken, @Param("comicId") Long comicId);

    List<ComicFavorite> listByUser(Long userId);
}