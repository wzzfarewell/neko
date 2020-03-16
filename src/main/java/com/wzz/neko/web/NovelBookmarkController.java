package com.wzz.neko.web;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.dto.BookmarkVo;
import com.wzz.neko.service.NovelBookmarkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * NovelBookmarkController
 *
 * @author wzzfarewell
 * @date 2020/03/11
 **/
@RestController
@RequestMapping("/novel/bookmark")
public class NovelBookmarkController {
    @Resource
    private NovelBookmarkService novelBookmarkService;

    @PostMapping("/add")
    public Result add(String token, Long novelId, Long chapterId) {
        int res = novelBookmarkService.addNovelBookmark(token, novelId, chapterId);
        if(res > 0) {
            return ResultGenerator.genSuccessResult(null, "添加书签成功");
        }else{
            return ResultGenerator.genFailResult("添加书签异常");
        }
    }

    @PostMapping("/delete")
    public Result delete(String token, Long novelId, Long chapterId) {
        int res = novelBookmarkService.deleteNovelBookmark(token, novelId, chapterId);
        if(res > 0) {
            return ResultGenerator.genSuccessResult(null, "删除书签成功");
        }else{
            return ResultGenerator.genFailResult("删除书签异常");
        }
    }

    @PostMapping("/list")
    public Result list(String token, Long novelId){
        List<BookmarkVo> bookmarkVos = novelBookmarkService.listByNovel(token, novelId);
        return ResultGenerator.genSuccessResult(bookmarkVos);
    }

}
