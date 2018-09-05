package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.SecondMenu;
import com.baomidou.springwind.entity.ext.SecondMenuExt;
import com.baomidou.springwind.mapper.SecondMenuDao;
import com.baomidou.springwind.service.ISecondMenuService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISecondMenuServiceImpl extends BaseServiceImpl<SecondMenuDao,SecondMenu> implements ISecondMenuService {

    @Autowired
    private SecondMenuDao secondMenuDao;

    @Override
    public List<SecondMenuExt> selectAll() {
        List<SecondMenuExt> secondMenuExts = secondMenuDao.selectAll();
        return secondMenuExts;
    }
}
