package com.wzz.neko.service;

import com.wzz.neko.core.Service;
import com.wzz.neko.model.Category;

import java.util.List;


/**
 * CategoryService
 *
 * @author wzzfarewell
 * @date 2020/02/28
 **/
public interface CategoryService extends Service<Category> {
        List<Category> findAllByCount();

        List<Category> topCategories();
}
