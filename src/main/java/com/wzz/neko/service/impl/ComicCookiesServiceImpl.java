package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.ComicCookiesMapper;
import com.wzz.neko.dto.ComicCookiesVo;
import com.wzz.neko.model.Comic;
import com.wzz.neko.model.ComicChapter;
import com.wzz.neko.model.ComicCookies;
import com.wzz.neko.service.ComicChapterService;
import com.wzz.neko.service.ComicCookiesService;
import com.wzz.neko.service.ComicService;
import com.wzz.neko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * ComicCookiesServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@Service
@Transactional
public class ComicCookiesServiceImpl extends AbstractService<ComicCookies> implements ComicCookiesService {
    @Resource
    private ComicCookiesMapper comicCookiesMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ComicService comicService;
    @Autowired
    private ComicChapterService comicChapterService;

    @Override
    public int addComicCookies(String token, Long comicId, Long chapterId) {
        ComicCookies cookies = new ComicCookies();
        cookies.setComicId(comicId);
        cookies.setUserId(userService.getUserIdByToken(token));
        cookies.setChapterId(chapterId);
        cookies.setStatus(Const.BookmarkStatus.NORMAL);
        cookies.setCreateTime(new Date());
        cookies.setUpdateTime(new Date());
        ComicCookies old = comicCookiesMapper.selectByComicUser(cookies.getUserId(), comicId);
        if(old != null){
            // 数据库中已经存在此用户对此书籍的浏览记录，则更新
            cookies.setId(old.getId());
            cookies.setCreateTime(old.getCreateTime());
            cookies.setUpdateTime(new Date());
            return comicCookiesMapper.updateByPrimaryKey(cookies);
        }else{
            return comicCookiesMapper.insert(cookies);
        }
    }

    @Override
    public int deleteComicCookies(String token, Long comicId, Long chapterId) {
        return comicCookiesMapper.updateStatus(userService.getUserIdByToken(token), comicId, chapterId);
    }

    @Override
    public List<ComicCookiesVo> listByUser(String token) {
        List<ComicCookies> cookiesList= comicCookiesMapper.listByUser(userService.getUserIdByToken(token));
        List<ComicCookiesVo> cookiesVos = new ArrayList<>();
        for(ComicCookies cookies : cookiesList){
            ComicCookiesVo cookiesVo = new ComicCookiesVo();
            cookiesVo.setComicId(cookies.getComicId());
            cookiesVo.setChapterId(cookies.getChapterId());
            cookiesVo.setCreateTime(cookies.getCreateTime());
            Comic comic = comicService.findById(cookies.getComicId());
            cookiesVo.setComicName(comic.getComicName());
            ComicChapter chapter = comicChapterService.findById(cookies.getChapterId());
            cookiesVo.setChapterName(chapter.getChapterName());
            cookiesVos.add(cookiesVo);
        }
        return cookiesVos;
    }
}
