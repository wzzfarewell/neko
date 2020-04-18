package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.Novel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface NovelMapper extends Mapper<Novel> {

    String getCoverPath(Long novelId);

    Date getLatestUpdateTime(Long novelId);

    String getStatus(Long novelId);

    List<Long> getLatestIdByCategory(@Param("category") String category, @Param("count") Integer count);

    List<Long> getHotIdByCategory(@Param("category") String category, @Param("count") Integer count);

    Novel getNovelByNameAndAuthor(@Param("name") String name,@Param("author") String author);

    List<Novel> listBySearchNovelVo(@Param("name") String name, @Param("sort") String sort, @Param("status") String status);

    List<Novel> listByBookNameOrAuthor(String param);

    int updateClickCountById(@Param("id") Long id, @Param("count") Long count);

    List<Novel> listTopNovels(Long count);

    List<Novel> listAll();
}