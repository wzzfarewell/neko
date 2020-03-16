package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.model.Chapter;

import java.util.List;


/**
 * ChapterService
 *
 * @author wzzfarewell
 * @date 2020/02/29
 **/
public interface ChapterService extends Service<Chapter> {
    List<Chapter> getChaptersByNovelId(Long novelId);
}
