package com.wzz.neko.dto;

import com.wzz.neko.model.Chapter;
import com.wzz.neko.model.Novel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * NovelDetailDto
 *
 * @author wzzfarewell
 * @date 2020/2/29
 **/
@Data
public class NovelDetailDto implements Serializable {
    private static final long serialVersionUID = 2760960578685886035L;
    private Novel novel;
    private List<Chapter> chapters;
    private String coverPath;
}
