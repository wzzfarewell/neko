package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.dao.ChapterMapper;
import com.wzz.neko.model.Chapter;
import com.wzz.neko.service.ChapterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;


/**
 * ChapterServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/02/29
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ChapterServiceImpl extends AbstractService<Chapter> implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public List<Chapter> getChaptersByNovelId(Long novelId) {
        List<Chapter> chapters = chapterMapper.listChaptersByNovelId(novelId);
        // 将小说章节排序
        chapters.sort(Comparator.comparingInt(Chapter::getChapterNum));
        for (Chapter c : chapters){
            c.setBookId(novelId);
        }
        return chapters;
    }
}
