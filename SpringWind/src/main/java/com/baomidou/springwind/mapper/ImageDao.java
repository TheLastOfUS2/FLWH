package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.Image;

public interface ImageDao {
    int deleteByPrimaryKey(String imageId);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}