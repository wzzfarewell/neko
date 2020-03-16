package com.wzz.neko.web;

import com.wzz.neko.annotations.NoToken;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.service.ComicChapterContentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ComicChapterContentController
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@RestController
@RequestMapping("/comic/chapter/content")
public class ComicChapterContentController {
    @Resource
    private ComicChapterContentService comicChapterContentService;

    @NoToken
    @PostMapping
    public Result detail(Long chapterId) {
        return ResultGenerator.genSuccessResult(comicChapterContentService.listByChapterId(chapterId));
    }

}
