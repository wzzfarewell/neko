package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.Chapter;

import java.util.List;

public interface ChapterMapper extends Mapper<Chapter> {

    List<Chapter> listChaptersByNovelId(Long novelId);
}