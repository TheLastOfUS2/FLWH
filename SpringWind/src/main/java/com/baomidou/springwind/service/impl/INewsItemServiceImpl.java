package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.NewsItem;
import com.baomidou.springwind.entity.ext.SecondMenuExt;
import com.baomidou.springwind.mapper.NewsItemDao;
import com.baomidou.springwind.service.INewsItemService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INewsItemServiceImpl extends BaseServiceImpl<NewsItemDao,NewsItem> implements INewsItemService {
    @Override
    public List<SecondMenuExt> selectAll() {
        return null;
    }

    @Override
    public SecondMenuExt selectByIdMy(String id) {
        return null;
    }
}
