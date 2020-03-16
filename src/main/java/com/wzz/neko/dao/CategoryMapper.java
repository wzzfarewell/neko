package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.Category;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {

    List<Category> findAllByCountDesc();

    /**
     * 获取小说数量最多的几个分类
     * @param len 获取的分类数量
     * @return 排序后最火的len个分类
     */
    List<Category> findTopCategories(Integer len);
}