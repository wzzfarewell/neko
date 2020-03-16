package com.wzz.neko.service.impl;

import com.wzz.neko.core.AbstractService;
import com.wzz.neko.core.Const;
import com.wzz.neko.dao.ComicMapper;
import com.wzz.neko.dto.ComicDetailDto;
import com.wzz.neko.model.Comic;
import com.wzz.neko.model.ComicChapter;
import com.wzz.neko.service.ComicChapterService;
import com.wzz.neko.service.ComicService;
import com.wzz.neko.utils.ImgUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;


/**
 * ComicServiceImpl
 *
 * @author wzzfarewell
 * @date 2020/03/14
 **/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ComicServiceImpl extends AbstractService<Comic> implements ComicService {
    @Resource
    private ComicMapper comicMapper;

    private final ComicChapterService comicChapterService;

    private final RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public ComicServiceImpl(ComicChapterService comicChapterService, RedisTemplate<Object, Object> redisTemplate) {
        this.comicChapterService = comicChapterService;
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
    }

    @Override
    public ComicDetailDto detail(Long comicId) {
        Comic comic = this.findById(comicId);
        // 增加点击量
        comic.setClickCount(comic.getClickCount() + Const.CLICK_COUNT);
        this.update(comic);
        // 获取漫画详情
        String key = Const.REDIS_COMIC_DETAIL_PREFIX + Long.toString(comicId);
        ComicDetailDto detailDto = (ComicDetailDto) redisTemplate.opsForValue().get(key);
        if(detailDto == null) {
            synchronized (this) {
                detailDto = (ComicDetailDto) redisTemplate.opsForValue().get(key);
                if(detailDto == null) {
                    List<ComicChapter> chapters = comicChapterService.listByComicId(comicId);
                    chapters.sort(Comparator.comparingInt(ComicChapter::getChapterNum));
                    detailDto = new ComicDetailDto();
                    comic.setCover(ImgUtils.getShowPath(comic.getCover()));
                    detailDto.setComic(comic);
                    detailDto.setChapters(chapters);
                    redisTemplate.opsForValue().set(key, detailDto);
                    log.info("从MySQL中查询ComicDetailDto");
                }else{
                    log.info("从Redis中查询ComicDetailDto");
                }
            }
        }else{
            log.info("从Redis中查询ComicDetailDto");
        }
        return detailDto;
    }

    @Override
    public List<Comic> search(String param) {
        param = "%" + param + "%";
        return comicMapper.listByNameOrAuthor(param);
    }
}
