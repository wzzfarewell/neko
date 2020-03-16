package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.ComicChapter;

import java.util.List;

public interface ComicChapterMapper extends Mapper<ComicChapter> {

    List<ComicChapter> listByComicId(Long comicId);
}