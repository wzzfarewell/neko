package com.wzz.neko.service;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.Service;
import com.wzz.neko.model.User;


/**
 * UserService
 *
 * @author wzzfarewell
 * @date 2020/1/9
 */
public interface UserService extends Service<User> {
    Result findByPage(int pageNum, int pageSize);

    Result registerUser(User user);

    Result activeUser(String code);

    Integer checkUsername(String username);

    /**
     * 验证登录时用户输入的用户名和密码，成功则携带token返回
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Result checkUsernameAndPwd(String username, String password);

    Result getUserByToken(String token);

    Long getUserIdByToken(String token);

    int updateUserPwd(String token, String oldPwd, String newPwd);

    Result updatePwdByEmail(String email);

    int updateUserPwdByCode(String activeCode, String newPwd);
}
