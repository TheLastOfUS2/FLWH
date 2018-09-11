package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.Image;

public interface ImageDao extends BaseMapper<Image> {
    void deleteByItemId(String itemId);
}