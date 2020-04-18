package com.wzz.neko.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * MailProperties
 *
 * @author wzzfarewell
 * @date 2020/3/16
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    @Value("${spring.mail.default-encoding}")
    private Charset defaultEncoding;
    private Map<String, String> properties = new HashMap<>();
}
