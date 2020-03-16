package com.wzz.neko.web;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.model.ComicChapter;
import com.wzz.neko.service.ComicChapterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ComicChapterController
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@RestController
@RequestMapping("/comic/chapter")
public class ComicChapterController {
    @Resource
    private ComicChapterService comicChapterService;

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        ComicChapter comicChapter = comicChapterService.findById(id);
        return ResultGenerator.genSuccessResult(comicChapter);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ComicChapter> list = comicChapterService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
