package com.wzz.neko.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzz.neko.core.AbstractService;
import com.wzz.neko.dao.UserMapper;
import com.wzz.neko.model.User;
import com.wzz.neko.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by wzz on 2019/10/22.
 */
@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = this.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
