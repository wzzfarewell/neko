package com.wzz.neko.web;

import com.wzz.neko.annotations.NoToken;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.dto.LoginInfo;
import com.wzz.neko.model.User;
import com.wzz.neko.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * UserController
 *
 * @author wzzfarewell
 * @date 2020/1/9
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody User user) {
        User u = userService.findById(id);
        BeanUtils.copyProperties(user, u);
        userService.update(u);
        return ResultGenerator.genSuccessResult(null, "修改用户信息成功");
    }

    @GetMapping("/")
    public Result list(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "8") Integer pageSize) {
        return userService.findByPage(pageNum, pageSize);
    }

    /**
     * 用户注册接口
     * @param user 以json格式封装的User对象
     * @param bindingResult 参数验证结果
     * @return 统一返回的json数据
     */
    @NoToken
    @PostMapping("/register")
    public Result register(@Valid @RequestBody User user, BindingResult bindingResult){
        // 入参进行参数校验
        StringBuilder errMsg = new StringBuilder();
        if(bindingResult.hasErrors()){
            for(FieldError fieldError : bindingResult.getFieldErrors()){
                errMsg.append(fieldError.getDefaultMessage()).append(";");
            }
            errMsg.delete(errMsg.lastIndexOf(";"), errMsg.length());
            return ResultGenerator.genFailResult(errMsg.toString());
        }
        if(userService.checkUsername(user.getUsername()) > 0){
            return ResultGenerator.genFailResult("用户名已存在");
        }
        return userService.registerUser(user);
    }

    @NoToken
    @GetMapping("/activeUser")
    public Result activeUser(String activeCode){
        return userService.activeUser(activeCode);
    }

    @NoToken
    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginInfo loginInfo){
        return userService.checkUsernameAndPwd(loginInfo.getUsername(), loginInfo.getPassword());
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(String token){
        return userService.getUserByToken(token);
    }

    @PostMapping("/updateUserPwd")
    public Result updateUserPwd(String token, String oldPwd, String newPwd){
        int res = userService.updateUserPwd(token, oldPwd, newPwd);
        if(res > 0){
            return ResultGenerator.genSuccessResult(null, "修改用户密码成功");
        }else{
            return ResultGenerator.genFailResult("修改用户密码失败");
        }
    }

}
