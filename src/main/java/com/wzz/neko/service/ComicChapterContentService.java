package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.model.ComicChapterContent;

import java.util.List;


/**
 * ComicChapterContentService
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
public interface ComicChapterContentService extends Service<ComicChapterContent> {
    List<ComicChapterContent> listByChapterId(Long chapterId);
}
