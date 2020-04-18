package com.wzz.neko.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultGenerator;
import com.wzz.neko.dao.UserMapper;
import com.wzz.neko.dto.MailVo;
import com.wzz.neko.exceptions.MailNotExistException;
import com.wzz.neko.exceptions.SendMailException;
import com.wzz.neko.model.User;
import com.wzz.neko.service.UserService;
import com.wzz.neko.utils.JWTUtils;
import com.wzz.neko.utils.MD5Utils;
import com.wzz.neko.utils.SendMailManage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


/**
 * UserServiceImpl
 * @author wzzfarewell
 * @date 2020/1/9
 */
@Slf4j
@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private LoadingCache<String, User> myLocalCache;

    @Override
    public Result findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = this.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result registerUser(User user) {
        user.setPassword(MD5Utils.MD5EncodeUtf8(user.getPassword()));
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setActiveCode(UUID.randomUUID().toString());
        user.setStatus(Const.UserStatus.NOT_ACTIVE);
        // 验证此邮箱是否已有注册用户使用
        if(null != userMapper.findByEmail(user.getEmail())){
            return ResultGenerator.genFailResult("此邮箱已被人注册，请更换邮箱重新注册");
        }
        // 发送激活邮件到用户注册的邮箱
        String emailMsg = "感谢您注册怪喵，点击<a href='" + Const.ACTIVE_USER_URL + user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。" + "<br />为保障您的账户安全，请在24小时内完成激活操作";
        MailVo mailVo = new MailVo();
        mailVo.setToEmail(user.getEmail());
        mailVo.setContent(emailMsg);
        mailVo.setSubject(Const.MAIL_SUBJECT_ACTIVE);
        boolean sendMailSuccess = false;
        try {
            sendMailSuccess = SendMailManage.sendMail(mailVo);
        } catch (SendMailException e) {
            return ResultGenerator.genFailResult("注册时发送激活邮件失败，失败原因：" + e.getMessage());
        } catch (MailNotExistException e){
            return ResultGenerator.genFailResult("注册时发送激活邮件失败，请使用有效的邮箱地址，失败原因：" + e.getMessage());
        }
        if(sendMailSuccess){
            try{
                userMapper.insert(user);
                return ResultGenerator.genSuccessResult(null, "用户：" + user.getUsername() + "注册成功！请使用注册的邮箱激活账号！");
            }catch (Exception e){
                log.error("exception: {}", e.toString());
                return ResultGenerator.genFailResult("用户注册失败，失败原因：" + e.getMessage());
            }
        }
        return ResultGenerator.genFailResult("服务器发送邮件繁忙，请稍后注册");
    }

    @Override
    public Result activeUser(String code) {
        User user = userMapper.selectByActiveCode(code);
        if(user != null){
            int resultCount = userMapper.updateUserStatusByCode(code, Const.UserStatus.ACTIVED);
            if(resultCount > 0){
                return ResultGenerator.genSuccessResult(null, "用户账号激活成功");
            }
        }
        return ResultGenerator.genFailResult("用户账号激活失败");
    }

    @Override
    public Integer checkUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Result checkUsernameAndPwd(String username, String password) {
        String md5Pwd = MD5Utils.MD5EncodeUtf8(password);
        User user = userMapper.selectByUsernameAndPwd(username, md5Pwd);
        if(user != null){
            if(user.getStatus() == Const.UserStatus.NOT_ACTIVE){
                return ResultGenerator.genFailResult("此账号暂未激活，请使用注册时的邮箱进行激活");
            }
            String token = JWTUtils.createToken(username, md5Pwd);
            log.info("created token: {}", token);
            myLocalCache.put(token, user);
            Map<String, Object> map = Maps.newHashMap();
            map.put("token", token);
            user.setPassword(StringUtils.EMPTY);
            map.put("user", user);
            return ResultGenerator.genSuccessResult(map, "登录成功");
        }
        return ResultGenerator.genFailResult("用户名或密码错误");
    }

    @Override
    public Result getUserByToken(String token) {
        try {
            User user = myLocalCache.get(token);
            if(user == null){
                return ResultGenerator.genFailResult("认证失败");
            }else{
                user.setPassword(StringUtils.EMPTY);
                return ResultGenerator.genSuccessResult(user, "认证成功");
            }
        } catch (ExecutionException e) {
            return ResultGenerator.genFailResult("认证失败");
        }
    }

    @Override
    public Long getUserIdByToken(String token) {
        User user = (User) this.getUserByToken(token).getData();
        return user.getId();
    }

    @Override
    public int updateUserPwd(String token, String oldPwd, String newPwd) {
        User user = (User) this.getUserByToken(token).getData();
        User u = userMapper.selectByUsernameAndPwd(user.getUsername(), MD5Utils.MD5EncodeUtf8(oldPwd));
        if(u != null){
            u.setPassword(MD5Utils.MD5EncodeUtf8(newPwd));
            return userMapper.updateByPrimaryKey(u);
        }
        return 0;
    }

    @Override
    public Result updatePwdByEmail(String email) {
        User user = userMapper.findByEmail(email);
        if(user == null){
            return ResultGenerator.genFailResult("此邮箱未被注册，请重新输入您注册时使用的邮箱");
        }else{
            String emailMsg = "您正在修改怪喵密码，点击<a href='" + Const.RESET_PWD_URL + user.getActiveCode() + "'>&nbsp;重置密码&nbsp;</a>进入密码重置页面。" + "<br />非本人操作请忽略！";
            MailVo mailVo = new MailVo();
            mailVo.setToEmail(user.getEmail());
            mailVo.setContent(emailMsg);
            mailVo.setSubject(Const.MAIL_SUBJECT_RESET_PWD);
            boolean sendMailSuccess = false;
            try {
                sendMailSuccess = SendMailManage.sendMail(mailVo);
            } catch (SendMailException e) {
                return ResultGenerator.genFailResult("发送重置密码邮件失败，失败原因：" + e.getMessage());
            } catch (MailNotExistException e){
                return ResultGenerator.genFailResult("发送重置密码邮件失败，请使用有效的邮箱地址，失败原因：" + e.getMessage());
            }
            if(sendMailSuccess){
                return ResultGenerator.genSuccessResult(null, "用户：" + user.getUsername() + "重置密码邮件已经发送到您的邮箱！");
            }
            return ResultGenerator.genFailResult("服务器发送邮件繁忙，请稍后注册");
        }
    }

    @Override
    public int updateUserPwdByCode(String activeCode, String newPwd) {
        User user = userMapper.selectByActiveCode(activeCode);
        if(null != user){
            user.setPassword(MD5Utils.MD5EncodeUtf8(newPwd));
            return userMapper.updateByPrimaryKey(user);
        }
        return 0;
    }

    //    public static void main(String[] args) {
//        System.out.println(MD5Utils.MD5EncodeUtf8("123456"));
//    }
}
