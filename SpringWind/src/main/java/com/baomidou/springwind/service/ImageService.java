package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.Image;

public interface ImageService extends IService<Image> {
    void deleteByItemId(String itemId);
}
