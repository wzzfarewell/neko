package com.wzz.neko.dto;

import com.wzz.neko.model.Comic;
import com.wzz.neko.model.ComicChapter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ComicDetailDto
 *
 * @author wzzfarewell
 * @date 2020/3/14
 **/
@Data
public class ComicDetailDto implements Serializable {
    private static final long serialVersionUID = -6069931441337116458L;
    private Comic comic;
    private List<ComicChapter> chapters;
}
