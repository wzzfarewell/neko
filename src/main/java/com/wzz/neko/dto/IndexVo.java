package com.wzz.neko.dto;

import com.wzz.neko.model.Novel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * IndexVo
 *
 * @author wzzfarewell
 * @date 2020/3/16
 **/
@Data
public class IndexVo implements Serializable {
    private static final long serialVersionUID = -4407872326059657296L;
    private Map<String, List<Novel>> latestCategoryNovelList;
    private Map<String, List<Novel>> hotCategoryNovelList;
    private List<Novel> recommends;
    private List<Novel> topNovels;
}
