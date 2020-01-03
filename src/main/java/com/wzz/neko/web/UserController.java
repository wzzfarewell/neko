package com.wzz.neko.web;
import com.github.pagehelper.PageInfo;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.model.User;
import com.wzz.neko.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by wzz on 2019/10/22.
*/
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/")
    public Result add(@RequestBody User user) {
        log.info("user: {}", user);
        userService.save(user);
        return ResultGenerator.genSuccessResult("添加用户成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult("删除用户成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody User user) {
        User u = userService.findById(id);
        BeanUtils.copyProperties(user, u);
        userService.update(u);
        return ResultGenerator.genSuccessResult("修改用户信息成功");
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping("/")
    public Result list(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "8") Integer pageSize) {
        PageInfo pageInfo = userService.findByPage(pageNum, pageSize);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
