package com.wzz.neko.service;
import com.wzz.neko.core.Service;
import com.wzz.neko.dto.NovelCookiesVo;
import com.wzz.neko.model.NovelCookies;

import java.util.List;


/**
 * NovelCookiesService
 *
 * @author wzzfarewell
 * @date 2020/03/11
 **/
public interface NovelCookiesService extends Service<NovelCookies> {

    int addNovelCookies(String token, Long novelId, Long chapterId);

    int deleteNovelCookies(String token, Long novelId, Long chapterId);

    List<NovelCookiesVo> listByUser(String token);
}
