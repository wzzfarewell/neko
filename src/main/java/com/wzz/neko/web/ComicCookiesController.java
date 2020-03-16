package com.wzz.neko.web;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.dto.ComicCookiesVo;
import com.wzz.neko.service.ComicCookiesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ComicCookiesController
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@RestController
@RequestMapping("/comic/cookies")
public class ComicCookiesController {
    @Resource
    private ComicCookiesService comicCookiesService;
    @PostMapping("/add")
    public Result add(String token, Long comicId, Long chapterId) {
        int res = comicCookiesService.addComicCookies(token, comicId, chapterId);
        if(res > 0) {
            return ResultGenerator.genSuccessResult(null, "添加浏览记录成功");
        }else{
            return ResultGenerator.genFailResult("添加浏览记录异常");
        }
    }

    @PostMapping("/delete")
    public Result delete(String token, Long comicId, Long chapterId) {
        int res = comicCookiesService.deleteComicCookies(token, comicId, chapterId);
        if(res > 0) {
            return ResultGenerator.genSuccessResult(null, "删除浏览记录成功");
        }else{
            return ResultGenerator.genFailResult("删除浏览记录异常");
        }
    }

    @PostMapping("/list")
    public Result list(String token){
        List<ComicCookiesVo> cookiesVos = comicCookiesService.listByUser(token);
        return ResultGenerator.genSuccessResult(cookiesVos);
    }

}
