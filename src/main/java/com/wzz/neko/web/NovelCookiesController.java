package com.wzz.neko.web;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.dto.NovelCookiesVo;
import com.wzz.neko.service.NovelCookiesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * NovelCookiesController
 *
 * @author wzzfarewell
 * @date 2020/03/11
 **/
@RestController
@RequestMapping("/novel/cookies")
public class NovelCookiesController {
    @Resource
    private NovelCookiesService novelCookiesService;

    @PostMapping("/add")
    public Result add(String token, Long novelId, Long chapterId) {
        int res = novelCookiesService.addNovelCookies(token, novelId, chapterId);
        if(res > 0) {
            return ResultGenerator.genSuccessResult(null, "添加浏览记录成功");
        }else{
            return ResultGenerator.genFailResult("添加浏览记录异常");
        }
    }

    @PostMapping("/delete")
    public Result delete(String token, Long novelId, Long chapterId) {
        int res = novelCookiesService.deleteNovelCookies(token, novelId, chapterId);
        if(res > 0) {
            return ResultGenerator.genSuccessResult(null, "删除浏览记录成功");
        }else{
            return ResultGenerator.genFailResult("删除浏览记录异常");
        }
    }

    @PostMapping("/list")
    public Result list(String token){
        List<NovelCookiesVo> cookiesVos = novelCookiesService.listByUser(token);
        return ResultGenerator.genSuccessResult(cookiesVos);
    }

}
