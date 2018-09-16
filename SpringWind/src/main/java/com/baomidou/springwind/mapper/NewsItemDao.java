package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.NewsItem;

public interface NewsItemDao extends BaseMapper<NewsItem> {
    int deleteByPrimaryKey(String newsItemId);

    int insertSelective(NewsItem record);

    NewsItem selectByPrimaryKey(String newsItemId);

    int updateByPrimaryKeySelective(NewsItem record);

    int updateByPrimaryKey(NewsItem record);
}