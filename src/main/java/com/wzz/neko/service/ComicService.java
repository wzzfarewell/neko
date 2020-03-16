package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.dto.ComicDetailDto;
import com.wzz.neko.model.Comic;

import java.util.List;


/**
 * ComicService
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
public interface ComicService extends Service<Comic> {
    ComicDetailDto detail(Long comicId);

    List<Comic> search(String param);
}
