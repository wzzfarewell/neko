package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.ComicChapterContent;

import java.util.List;

public interface ComicChapterContentMapper extends Mapper<ComicChapterContent> {

    List<ComicChapterContent> listByChapterId(Long chapterId);
}