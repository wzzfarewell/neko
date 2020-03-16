package com.wzz.neko.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * MyConfigInfo
 *
 * @author wzzfarewell
 * @date 2020/3/1
 **/
@Data
@Component
@ConfigurationProperties(prefix = "my-config-info")
public class MyConfigInfo {
    private List<String> recommendList = new ArrayList<>();
}
