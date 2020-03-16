package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.NovelBookmarkMapper;
import com.wzz.neko.dto.BookmarkVo;
import com.wzz.neko.model.Chapter;
import com.wzz.neko.model.NovelBookmark;
import com.wzz.neko.service.ChapterService;
import com.wzz.neko.service.NovelBookmarkService;
import com.wzz.neko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * NovelBookmarkServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/11
 **/
@Service
@Transactional
public class NovelBookmarkServiceImpl extends AbstractService<NovelBookmark> implements NovelBookmarkService {
    @Resource
    private NovelBookmarkMapper novelBookmarkMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ChapterService chapterService;

    @Override
    public int addNovelBookmark(String token, Long novelId, Long chapterId) {
        NovelBookmark bookmark = new NovelBookmark();
        bookmark.setUserId(userService.getUserIdByToken(token));
        bookmark.setBookId(novelId);
        bookmark.setChapterId(chapterId);
        bookmark.setStatus(Const.BookmarkStatus.NORMAL);
        bookmark.setCreateTime(new Date());
        bookmark.setUpdateTime(new Date());
        return novelBookmarkMapper.insert(bookmark);
    }

    @Override
    public int deleteNovelBookmark(String token, Long novelId, Long chapterId) {
        return novelBookmarkMapper.updateStatus(userService.getUserIdByToken(token), novelId, chapterId);
    }

    @Override
    public List<BookmarkVo> listByNovel(String token, Long novelId) {
        List<NovelBookmark> novelBookmarks = novelBookmarkMapper.listByUserNovel(userService.getUserIdByToken(token), novelId);
        List<BookmarkVo> bookmarkVos = new ArrayList<>();
        for(NovelBookmark bookmark : novelBookmarks){
            BookmarkVo bookmarkVo = new BookmarkVo();
            bookmarkVo.setNovelId(bookmark.getBookId());
            bookmarkVo.setChapterId(bookmark.getChapterId());
            bookmarkVo.setCreateTime(bookmark.getCreateTime());
            Chapter chapter = chapterService.findById(bookmark.getChapterId());
            bookmarkVo.setChapterName(chapter.getChapterName());
            bookmarkVos.add(bookmarkVo);
        }
        return bookmarkVos;
    }
}
