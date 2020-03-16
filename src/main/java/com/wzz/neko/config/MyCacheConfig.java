package com.wzz.neko.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.wzz.neko.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * MyCacheConfig
 *
 * @author wzzfarewell
 * @date 2020/1/10
 **/
@Configuration
public class MyCacheConfig {

    /**
     * 本地缓存，初始值1000，最大值10000超过最大值使用LRU算法清除，有效时间24小时
     * 用来存储登录用户的token和对应的User对象
     */
    @Bean
    public LoadingCache<String, User> myLocalCache(){
        return CacheBuilder.newBuilder()
                .initialCapacity(1000).maximumSize(10000).expireAfterAccess(24, TimeUnit.HOURS)
                .build(new CacheLoader<String, User>() {
                    // 默认的数据加载实现，如果调用get取值的时候key没有对应的值，就调用这个方法
                    @Override
                    public User load(String s) throws Exception {
                        return null;
                    }
                });
    }
}
