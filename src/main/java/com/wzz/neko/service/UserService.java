package com.wzz.neko.service;

import com.github.pagehelper.PageInfo;
import com.wzz.neko.core.Service;
import com.wzz.neko.model.User;


/**
 * Created by wzz on 2019/10/22.
 */
public interface UserService extends Service<User> {
    PageInfo findByPage(int pageNum, int pageSize);
}
