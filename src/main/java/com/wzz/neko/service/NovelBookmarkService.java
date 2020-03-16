package com.wzz.neko.service;
import com.wzz.neko.dto.BookmarkVo;
import com.wzz.neko.model.NovelBookmark;
import com.wzz.neko.core.Service;

import java.util.List;


/**
 * NovelBookmarkService
 *
 * @author wzzfarewell
 * @date 2020/03/11
 **/
public interface NovelBookmarkService extends Service<NovelBookmark> {

    int addNovelBookmark(String token, Long novelId, Long chapterId);

    int deleteNovelBookmark(String token, Long novelId, Long chapterId);

    List<BookmarkVo> listByNovel(String token, Long novelId);
}
