package com.wzz.neko.config;

import com.google.common.cache.LoadingCache;
import com.wzz.neko.annotations.NoToken;
import com.wzz.neko.exceptions.AuthException;
import com.wzz.neko.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * AuthenticationInterceptor
 * 认证拦截器
 * @author wzzfarewell
 * @date 2020/1/10
 **/
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private LoadingCache<String, User> loadingCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法的话直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查接口方法上是否有NoToken注解
        if(method.isAnnotationPresent(NoToken.class)){
            // NoToken注解中的required属性为true则直接通过
            return method.getAnnotation(NoToken.class).required();
        }else{
            String token = request.getHeader("Authorization");
            log.info("request token: {}", token);
            if(token == null){
                throw new AuthException("用户认证失败");
            }
            // 验证前端传入的token是否有效
            User user = loadingCache.get(token);
            if(user == null){
                throw new AuthException("用户认证失败");
            }else{
                log.info("用户：{} 认证成功！", user);
//                String md5Pwd = MD5Utils.MD5EncodeUtf8(user.getPassword());
//                try{
//                    JWTUtils.verify(token, md5Pwd);
//                    log.info("用户：{} 认证成功！", user);
//                }catch (JWTVerificationException e){
//                    throw new AuthException("token已过期");
//                }
            }
            return true;
        }
    }

}
