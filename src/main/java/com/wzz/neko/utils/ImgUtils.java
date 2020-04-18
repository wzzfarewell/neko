package com.wzz.neko.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * ImgUtils
 *
 * @author wzzfarewell
 * @date 2020/3/14
 **/
@Slf4j
public class ImgUtils {

    public static String getShowPath(String source){
        String showPath = source.replace("/home/NekoFiles/ComicCovers", "/img/cover");
        showPath = "http://111.229.58.19:8090" + showPath;
        return showPath;
    }

    public static String getContentShowPath(String source){
        String showPath = source.replace("/home/NekoFiles/Comics", "/img/content");
        showPath = "http://111.229.58.19:8090" + showPath;
        return showPath;
    }

}
