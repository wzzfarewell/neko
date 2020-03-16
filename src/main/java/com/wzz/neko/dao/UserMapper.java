package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends Mapper<User> {

    int findByUsername(String username);

    User selectByUsernameAndPwd(@Param("name") String username, @Param("pwd") String password);

    User selectByActiveCode(String code);

    int updateUserStatusByCode(@Param("code") String code, @Param("status") Integer status);

}