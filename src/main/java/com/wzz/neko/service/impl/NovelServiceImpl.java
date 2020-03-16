package com.wzz.neko.service.impl;

import com.wzz.neko.config.MyConfigInfo;
import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.NovelMapper;
import com.wzz.neko.dto.IndexVo;
import com.wzz.neko.dto.NovelDetailDto;
import com.wzz.neko.dto.SearchNovelVo;
import com.wzz.neko.model.Category;
import com.wzz.neko.model.Chapter;
import com.wzz.neko.model.Novel;
import com.wzz.neko.service.CategoryService;
import com.wzz.neko.service.ChapterService;
import com.wzz.neko.service.NovelService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * NovelServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/02/28
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NovelServiceImpl extends AbstractService<Novel> implements NovelService {
    @Resource
    private NovelMapper novelMapper;

    private final ChapterService chapterService;

    private final CategoryService categoryService;

    private final MyConfigInfo myConfigInfo;

    private final RedisTemplate<Object, Object> redisTemplate;

    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    public NovelServiceImpl(ChapterService chapterService, CategoryService categoryService, MyConfigInfo myConfigInfo, RedisTemplate<Object, Object> redisTemplate) {
        this.chapterService = chapterService;
        this.categoryService = categoryService;
        this.myConfigInfo = myConfigInfo;
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    }

    @Override
    public NovelDetailDto getNovelDetail(Long novelId) {
        // 用户点击此小说，点击量增加CLICK_COUNT
        novelMapper.updateClickCountById(novelId, Const.CLICK_COUNT);
        // 获取小说详情及章节信息（不包含内容）
        String key = Const.REDIS_NOVEL_DETAIL_PREFIX + Long.toString(novelId);
        NovelDetailDto novelDetailDto = (NovelDetailDto) redisTemplate.opsForValue().get(key);
        if(novelDetailDto == null){
            synchronized (this){
                novelDetailDto = (NovelDetailDto) redisTemplate.opsForValue().get(key);
                if(novelDetailDto == null) {
                    novelDetailDto = new NovelDetailDto();
                    Novel novel = novelMapper.selectByPrimaryKey(novelId);
                    List<Chapter> chapters = chapterService.getChaptersByNovelId(novelId);
                    if(!chapters.isEmpty()){
                        novel.setStatus(novelMapper.getStatus(novelId));
                        novel.setLatestUpdateTime(novelMapper.getLatestUpdateTime(novelId));
                        novel.setLatestChapterName(chapters.get(chapters.size() - 1).getChapterName());
                        novelDetailDto.setChapters(chapters);
                    }
                    novelDetailDto.setNovel(novel);
                    novelDetailDto.setCoverPath(novelMapper.getCoverPath(novelId));
                    redisTemplate.opsForValue().set(key, novelDetailDto);
                    log.info("从MySQL中查询NovelDetailDto");
                }else{
                    log.info("从Redis中查询NovelDetailDto");
                }
            }
        }else{
            log.info("从Redis中查询NovelDetailDto");
        }
        return novelDetailDto;
    }

    @Override
    public List<Long> getLatestNovelByCategory(String category) {
        return novelMapper.getLatestIdByCategory(category, Const.RECOMMEND_COUNT);
    }

    @Override
    public List<Long> getHotNovelByCategory(String category) {
        return novelMapper.getHotIdByCategory(category, Const.RECOMMEND_COUNT);
    }

    @Override
    public Map<String, List<Novel>> getLatestCategoryNovelList() {
        Map<String, List<Novel>> result = new HashMap<>(8);
        List<Category> categories = categoryService.topCategories();
        for(Category category : categories){
            List<Novel> novels = new ArrayList<>();
            List<Long> latestNovelIds = this.getLatestNovelByCategory(category.getName());
            for(Long id : latestNovelIds){
                novels.add(novelMapper.selectByPrimaryKey(id));
            }
            result.put(category.getName(), novels);
        }
        return result;
    }

    @Override
    public Map<String, List<Novel>> getHotCategoryNovelList() {
        Map<String, List<Novel>> result = new HashMap<>(8);
        List<Category> categories = categoryService.topCategories();
        for(Category category : categories){
            List<Novel> novels = new ArrayList<>();
            List<Long> latestNovelIds = this.getHotNovelByCategory(category.getName());
            for(Long id : latestNovelIds){
                novels.add(novelMapper.selectByPrimaryKey(id));
            }
            result.put(category.getName(), novels);
        }
        return result;
    }

    @Override
    public List<Novel> getRecommendNovels() {
        List<Novel> novels = new ArrayList<>();
        for(String s : myConfigInfo.getRecommendList()){
            String name = s.split(";")[0];
            String author = s.split(";")[1];
            novels.add(novelMapper.getNovelByNameAndAuthor(name, author));
        }
        return novels;
    }

    @Override
    public List<Novel> getTopNovels() {
        return novelMapper.listTopNovels(Const.TOP_COUNT);
    }

    @Override
    public IndexVo getIndexData() {
        // 每天将首页数据更新到redis
        String key = Const.REDIS_NOVEL_INDEX_PREFIX + DateTime.now().toString(dateTimeFormatter);
        IndexVo indexVo = (IndexVo) redisTemplate.opsForValue().get(key);
        if(indexVo == null){
            synchronized (this){
                indexVo = (IndexVo) redisTemplate.opsForValue().get(key);
                if(indexVo == null){
                    indexVo = new IndexVo();
                    indexVo.setLatestCategoryNovelList(this.getLatestCategoryNovelList());
                    indexVo.setHotCategoryNovelList(this.getHotCategoryNovelList());
                    indexVo.setRecommends(this.getRecommendNovels());
                    indexVo.setTopNovels(this.getTopNovels());
                    redisTemplate.opsForValue().set(key, indexVo);
                    log.info("从MySQL中查询IndexVo");
                }else{
                    log.info("从Redis中查询IndexVo");
                }
            }
        }else{
            log.info("从Redis中查询IndexVo");
        }
        return indexVo;
    }


    @Override
    public List<Novel> listByConditions(SearchNovelVo searchNovelVo) {
        return novelMapper.listBySearchNovelVo(searchNovelVo.getCategoryName(), searchNovelVo.getSort(), searchNovelVo.getStatus());
    }

    @Override
    public List<Novel> search(String param) {
        param = "%" + param + "%";
        return novelMapper.listByBookNameOrAuthor(param);
    }
}
