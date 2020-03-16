package com.wzz.neko.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * LoginInfo
 *
 * @author wzzfarewell
 * @date 2020/1/9
 **/
@Data
public class LoginInfo {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "登录密码不能为空")
    private String password;
}
