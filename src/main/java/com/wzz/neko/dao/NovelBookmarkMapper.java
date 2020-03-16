package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.NovelBookmark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NovelBookmarkMapper extends Mapper<NovelBookmark> {

    int updateStatus(@Param("userId") Long userId, @Param("novelId") Long novelId, @Param("chapterId") Long chapterId);

    List<NovelBookmark> listByUserNovel(@Param("userId") Long userId, @Param("novelId") Long novelId);
}