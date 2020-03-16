package com.wzz.neko.web;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.model.Comic;
import com.wzz.neko.service.ComicFavoriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ComicFavoriteController
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@RestController
@RequestMapping("/comic/favorite")
public class ComicFavoriteController {
    @Resource
    private ComicFavoriteService comicFavoriteService;

    @PostMapping("/add")
    public Result add(String token, Long comicId) {
        int res = comicFavoriteService.addFavoriteComic(token, comicId);
        if(res > 0){
            return ResultGenerator.genSuccessResult(null, "添加到收藏夹成功");
        }
        return ResultGenerator.genFailResult("添加到收藏夹异常");
    }

    @PostMapping("/delete")
    public Result delete(String token, Long comicId) {
        int res = comicFavoriteService.deleteFavoriteComic(token, comicId);
        if(res > 0){
            return ResultGenerator.genSuccessResult(null, "从收藏夹删除漫画成功");
        }
        return ResultGenerator.genFailResult("从收藏夹删除漫画失败");
    }

    @PostMapping("/my")
    public Result myFavorites(String token){
        List<Comic> comics = comicFavoriteService.listFavoriteComics(token);
        return ResultGenerator.genSuccessResult(comics);
    }
}
