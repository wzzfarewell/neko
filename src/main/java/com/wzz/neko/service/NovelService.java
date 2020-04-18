package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.dto.IndexVo;
import com.wzz.neko.dto.NovelDetailDto;
import com.wzz.neko.dto.SearchNovelVo;
import com.wzz.neko.model.Novel;

import java.util.List;
import java.util.Map;


/**
 * NovelService
 *
 * @author wzzfarewell
 * @date 2020/02/28
 **/
public interface NovelService extends Service<Novel> {
    NovelDetailDto getNovelDetail(Long novelId);

    List<Long> getLatestNovelByCategory(String category);

    List<Long> getHotNovelByCategory(String category);

    Map<String, List<Novel>> getLatestCategoryNovelList();

    Map<String, List<Novel>> getHotCategoryNovelList();

    List<Novel> getRecommendNovels();

    List<Novel> getTopNovels();

    IndexVo getIndexData();

    List<Novel> listByConditions(SearchNovelVo searchNovelVo);

    List<Novel> search(String param);

    List<Novel> getAll();

}
