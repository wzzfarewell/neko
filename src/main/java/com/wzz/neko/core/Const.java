package com.wzz.neko.core;

import com.wzz.neko.utils.PropertiesUtils;

/**
 * Const
 *
 * @author wzzfarewell
 * @date 2020/1/7
 **/
public class Const {

    public interface Role{
        int ROLE_CUSTOMER = 0;  // 普通用户
        int ROLE_ADMIN = 1;     // 管理员
    }

    public interface UserStatus{
        int NOT_ACTIVE = 0;     // 账号未激活
        int ACTIVED = 1;        // 账号已激活
    }

    public interface BookmarkStatus{
        int DELETED = 0;     // 被删除
        int NORMAL = 1;      // 正常
    }

    public static final String ACTIVE_USER_URL = PropertiesUtils.getProperty("activeUserUrl");
    public static final String RESET_PWD_URL = PropertiesUtils.getProperty("resetPasswordUrl");
    public static final String REDIS_NOVEL_DETAIL_PREFIX = "Novel_Detail_";
    public static final String REDIS_NOVEL_INDEX_PREFIX = "Novel_INDEX_";
    public static final String REDIS_COMIC_DETAIL_PREFIX = "Comic_Detail_";
    public static Integer RECOMMEND_COUNT = Integer.valueOf(PropertiesUtils.getProperty("recommendCount"));
    public static Integer INDEX_CATEGORY_COUNT = Integer.valueOf(PropertiesUtils.getProperty("indexCategoryCount"));
    public static Long CLICK_COUNT = Long.valueOf(PropertiesUtils.getProperty("clickCount"));
    public static Long TOP_COUNT = 10L;

    public static final String ENV_DEV = "dev";
    public static final String ENV_PRO = "prod";

    public static final String MAIL_SUBJECT_ACTIVE = "注册用户激活";
    public static final String MAIL_SUBJECT_RESET_PWD = "重置用户密码";
}
