package com.wzz.neko.service.impl;

import com.wzz.neko.dao.ComicChapterContentMapper;
import com.wzz.neko.model.ComicChapterContent;
import com.wzz.neko.service.ComicChapterContentService;
import com.wzz.neko.core.AbstractService;
import com.wzz.neko.utils.ImgUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


/**
 * ComicChapterContentServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@Service
@Transactional
public class ComicChapterContentServiceImpl extends AbstractService<ComicChapterContent> implements ComicChapterContentService {
    @Resource
    private ComicChapterContentMapper comicChapterContentMapper;

    @Override
    public List<ComicChapterContent> listByChapterId(Long chapterId) {
        List<ComicChapterContent> contents = comicChapterContentMapper.listByChapterId(chapterId);
        contents.sort(Comparator.comparingInt(ComicChapterContent::getContentNum));
        Iterator<ComicChapterContent> it = contents.iterator();
        while(it.hasNext()){
            ComicChapterContent content = it.next();
            content.setPath(ImgUtils.getContentShowPath(content.getPath()));
        }
        return contents;
    }
}
