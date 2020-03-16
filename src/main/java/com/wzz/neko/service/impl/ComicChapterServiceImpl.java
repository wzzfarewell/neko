package com.wzz.neko.service.impl;

import com.wzz.neko.dao.ComicChapterMapper;
import com.wzz.neko.model.ComicChapter;
import com.wzz.neko.service.ComicChapterService;
import com.wzz.neko.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * ComicChapterServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@Service
@Transactional
public class ComicChapterServiceImpl extends AbstractService<ComicChapter> implements ComicChapterService {
    @Resource
    private ComicChapterMapper comicChapterMapper;

    @Override
    public List<ComicChapter> listByComicId(Long comicId) {
        return comicChapterMapper.listByComicId(comicId);
    }
}
