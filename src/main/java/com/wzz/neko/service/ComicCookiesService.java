package com.wzz.neko.service;
import com.wzz.neko.dto.ComicCookiesVo;
import com.wzz.neko.model.ComicCookies;
import com.wzz.neko.core.Service;

import java.util.List;


/**
 * ComicCookiesService
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
public interface ComicCookiesService extends Service<ComicCookies> {

    int addComicCookies(String token, Long comicId, Long chapterId);

    int deleteComicCookies(String token, Long comicId, Long chapterId);

    List<ComicCookiesVo> listByUser(String token);
}
