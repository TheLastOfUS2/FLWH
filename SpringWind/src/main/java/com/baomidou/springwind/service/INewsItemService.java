package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.NewsItem;
import com.baomidou.springwind.entity.SecondMenu;
import com.baomidou.springwind.entity.ext.SecondMenuExt;

import java.util.List;

public interface INewsItemService extends IService<NewsItem> {
    List<SecondMenuExt> selectAll();

    SecondMenuExt selectByIdMy(String id);
}
