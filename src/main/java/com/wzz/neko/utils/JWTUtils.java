package com.wzz.neko.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

/**
 * JWTUtils
 *
 * @author wzzfarewell
 * @date 2020/1/10
 **/
public class JWTUtils {

    /**
     * 使用JWT根据用户名和密码创建token
     * @param username
     * @param password
     * @return
     */
    public static String createToken(String username, String password){
        return JWT.create()
                .withIssuer(username)
                // token过期时间，这里设为24小时
                .withExpiresAt(new Date(System.currentTimeMillis() + (24 * 3600 * 1000)))
                .sign(Algorithm.HMAC256(password));
    }

    /**
     * 验证token是否过期
     * @param token 要验证的token
     * @param secret 之前使用的加密秘钥，这里是用户的密码
     * @throws JWTVerificationException
     */
    public static void verify(String token, String secret) throws JWTVerificationException {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

}
