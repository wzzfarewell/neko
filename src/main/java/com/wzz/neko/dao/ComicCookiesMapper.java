package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.ComicCookies;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComicCookiesMapper extends Mapper<ComicCookies> {
    ComicCookies selectByComicUser(@Param("userId") Long userId, @Param("comicId") Long comicId);

    int updateStatus(@Param("userId") Long userId, @Param("comicId") Long comicId, @Param("chapterId") Long chapterId);

    List<ComicCookies> listByUser(Long userId);
}