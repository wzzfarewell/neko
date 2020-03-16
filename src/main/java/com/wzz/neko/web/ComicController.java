package com.wzz.neko.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzz.neko.annotations.NoToken;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.dto.SearchNovelVo;
import com.wzz.neko.model.Comic;
import com.wzz.neko.service.ComicService;
import com.wzz.neko.utils.ImgUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * ComicController
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@RestController
@RequestMapping("/comic")
public class ComicController {
    @Resource
    private ComicService comicService;

    @NoToken
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(comicService.detail(id));
    }

    @NoToken
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comic> list = comicService.findAll();
        Iterator<Comic> it = list.iterator();
        while(it.hasNext()){
            Comic c = it.next();
            c.setCover(ImgUtils.getShowPath(c.getCover()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @NoToken
    @PostMapping("/search")
    public Result search(@RequestBody SearchNovelVo searchNovelVo){
        PageHelper.startPage(searchNovelVo.getPageNum(), searchNovelVo.getPageSize());
        List<Comic> comics = comicService.search(searchNovelVo.getNameOrAuthor());
        if(comics.isEmpty()){
            return ResultGenerator.genFailResult("要查询的漫画或作者不存在");
        }
        Iterator<Comic> it = comics.iterator();
        while(it.hasNext()){
            Comic c = it.next();
            c.setCover(ImgUtils.getShowPath(c.getCover()));
        }
        PageInfo pageInfo = new PageInfo(comics);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
