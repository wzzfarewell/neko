package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.CategoryMapper;
import com.wzz.neko.model.Category;
import com.wzz.neko.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * CategoryServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/02/28
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
    @Resource
    private CategoryMapper novelCategoryMapper;

    @Override
    public List<Category> findAllByCount() {
        List<Category> categories = novelCategoryMapper.findAllByCountDesc();
        return categories;
    }

    @Override
    public List<Category> topCategories() {
        return novelCategoryMapper.findTopCategories(Const.INDEX_CATEGORY_COUNT);
    }
}
