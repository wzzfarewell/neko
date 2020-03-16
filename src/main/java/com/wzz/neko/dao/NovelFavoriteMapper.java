package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.NovelFavorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NovelFavoriteMapper extends Mapper<NovelFavorite> {

    List<NovelFavorite> listByUserId(Long userId);

    int deleteByUserNovelId(@Param("userId") Long userId, @Param("novelId") Long novelId);
}