package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.NovelCookies;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NovelCookiesMapper extends Mapper<NovelCookies> {
    int updateStatus(@Param("userId") Long userId, @Param("novelId") Long novelId, @Param("chapterId") Long chapterId);

    List<NovelCookies> listByUser(Long userId);

    NovelCookies selectByNovelUser(@Param("userId") Long userId, @Param("novelId") Long novelId);
}