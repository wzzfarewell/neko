package com.wzz.neko.web;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.model.Novel;
import com.wzz.neko.service.NovelFavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * NovelFavoriteController
 *
 * @author wzzfarewell
 * @date 2020/03/10
 **/
@Slf4j
@RestController
@RequestMapping("/novel/favorite")
public class NovelFavoriteController {
    @Resource
    private NovelFavoriteService novelFavoriteService;

    @PostMapping("/add")
    public Result add(String token, Long novelId) {
        int res = novelFavoriteService.addFavoriteNovel(token, novelId);
        if(res > 0){
            return ResultGenerator.genSuccessResult(null, "添加到收藏夹成功");
        }
        return ResultGenerator.genFailResult("添加到收藏夹异常");
    }

    @PostMapping("/delete")
    public Result delete(String token, Long novelId) {
        int res = novelFavoriteService.deleteFavoriteNovel(token, novelId);
        if(res > 0){
            return ResultGenerator.genSuccessResult(null, "从收藏夹删除小说成功");
        }
        return ResultGenerator.genFailResult("从收藏夹删除小说失败");
    }

    @PostMapping("/my")
    public Result myFavorites(String token){
        List<Novel> novels = novelFavoriteService.listFavoriteNovels(token);
        return ResultGenerator.genSuccessResult(novels);
    }
}
