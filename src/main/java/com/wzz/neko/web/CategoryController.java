package com.wzz.neko.web;

import com.wzz.neko.annotations.NoToken;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.model.Category;
import com.wzz.neko.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * CategoryController
 *
 * @author wzzfarewell
 * @date 2020/02/28
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResultGenerator.genSuccessResult(category);
    }

/*    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Category> list = categoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }*/

    @NoToken
    @GetMapping("/all")
    public Result listAll(){
        return ResultGenerator.genSuccessResult(categoryService.findAllByCount());
    }

}
