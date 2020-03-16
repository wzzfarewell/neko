package com.wzz.neko.web;

import com.wzz.neko.annotations.NoToken;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.model.Chapter;
import com.wzz.neko.service.ChapterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ChapterController
 *
 * @author wzzfarewell
 * @date 2020/02/29
 **/
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @NoToken
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Chapter chapter = chapterService.findById(id);
        return ResultGenerator.genSuccessResult(chapter);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Chapter> list = chapterService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
