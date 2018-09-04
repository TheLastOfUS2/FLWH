package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.NewsItem;

public interface NewsItemDao {
    int deleteByPrimaryKey(String newsItemId);

    int insert(NewsItem record);

    int insertSelective(NewsItem record);

    NewsItem selectByPrimaryKey(String newsItemId);

    int updateByPrimaryKeySelective(NewsItem record);

    int updateByPrimaryKey(NewsItem record);
}