package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.model.ComicChapter;

import java.util.List;


/**
 * ComicChapterService
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
public interface ComicChapterService extends Service<ComicChapter> {
    List<ComicChapter> listByComicId(Long comicId);
}
