package com.wzz.neko.dao;

import com.wzz.neko.core.Mapper;
import com.wzz.neko.model.Comic;

import java.util.List;

public interface ComicMapper extends Mapper<Comic> {
    List<Comic> listByNameOrAuthor(String param);
}