package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.NovelCookiesMapper;
import com.wzz.neko.dto.NovelCookiesVo;
import com.wzz.neko.model.Chapter;
import com.wzz.neko.model.Novel;
import com.wzz.neko.model.NovelCookies;
import com.wzz.neko.service.ChapterService;
import com.wzz.neko.service.NovelCookiesService;
import com.wzz.neko.service.NovelService;
import com.wzz.neko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * NovelCookiesServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/11
 **/
@Service
@Transactional
public class NovelCookiesServiceImpl extends AbstractService<NovelCookies> implements NovelCookiesService {
    @Resource
    private NovelCookiesMapper novelCookiesMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private NovelService novelService;
    @Autowired
    private ChapterService chapterService;

    @Override
    public int addNovelCookies(String token, Long novelId, Long chapterId) {
        NovelCookies cookies = new NovelCookies();
        cookies.setBookId(novelId);
        cookies.setUserId(userService.getUserIdByToken(token));
        cookies.setChapterId(chapterId);
        cookies.setStatus(Const.BookmarkStatus.NORMAL);
        cookies.setCreateTime(new Date());
        cookies.setUpdateTime(new Date());
        NovelCookies old = novelCookiesMapper.selectByNovelUser(cookies.getUserId(), novelId);
        if(old != null){
            // 数据库中已经存在此用户对此书籍的浏览记录，则更新
            cookies.setId(old.getId());
            cookies.setCreateTime(old.getCreateTime());
            cookies.setUpdateTime(new Date());
            return novelCookiesMapper.updateByPrimaryKey(cookies);
        }else{
            return novelCookiesMapper.insert(cookies);
        }
    }

    @Override
    public int deleteNovelCookies(String token, Long novelId, Long chapterId) {
        return novelCookiesMapper.updateStatus(userService.getUserIdByToken(token), novelId, chapterId);
    }

    @Override
    public List<NovelCookiesVo> listByUser(String token) {
        List<NovelCookies> cookiesList= novelCookiesMapper.listByUser(userService.getUserIdByToken(token));
        List<NovelCookiesVo> cookiesVos = new ArrayList<>();
        for(NovelCookies cookies : cookiesList){
            NovelCookiesVo cookiesVo = new NovelCookiesVo();
            cookiesVo.setNovelId(cookies.getBookId());
            cookiesVo.setChapterId(cookies.getChapterId());
            cookiesVo.setCreateTime(cookies.getCreateTime());
            Novel novel = novelService.findById(cookies.getBookId());
            cookiesVo.setNovelName(novel.getBookName());
            Chapter chapter = chapterService.findById(cookies.getChapterId());
            cookiesVo.setChapterName(chapter.getChapterName());
            cookiesVos.add(cookiesVo);
        }
        return cookiesVos;
    }
}
