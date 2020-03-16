package com.wzz.neko.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzz.neko.annotations.NoToken;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.dto.NovelDetailDto;
import com.wzz.neko.dto.SearchNovelVo;
import com.wzz.neko.model.Novel;
import com.wzz.neko.service.NovelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * NovelController
 *
 * @author wzzfarewell
 * @date 2020/02/28
 **/
@Slf4j
@RestController
@RequestMapping("/novel")
public class NovelController {
    @Resource
    private NovelService novelService;

    @NoToken
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        NovelDetailDto novelDetailDto = novelService.getNovelDetail(id);
        if(novelDetailDto == null){
            return ResultGenerator.genFailResult("查询小说详情失败！");
        }
        return ResultGenerator.genSuccessResult(novelDetailDto);
    }

    @NoToken
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Novel> list = novelService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @NoToken
    @PostMapping("/category")
    public Result listByConditions(@RequestBody SearchNovelVo searchNovelVo) {
        PageHelper.startPage(searchNovelVo.getPageNum(), searchNovelVo.getPageSize());
        List<Novel> list = novelService.listByConditions(searchNovelVo);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @NoToken
    @PostMapping("/search")
    public Result search(@RequestBody SearchNovelVo searchNovelVo){
        PageHelper.startPage(searchNovelVo.getPageNum(), searchNovelVo.getPageSize());
        List<Novel> novels = novelService.search(searchNovelVo.getNameOrAuthor());
        if(novels.isEmpty()){
            return ResultGenerator.genFailResult("要查询的小说或作者不存在");
        }
        PageInfo pageInfo = new PageInfo(novels);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @NoToken
    @GetMapping("/index")
    public Result index(){
        return ResultGenerator.genSuccessResult(novelService.getIndexData());
    }

}
