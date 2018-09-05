package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.SecondMenu;
import com.baomidou.springwind.entity.ext.SecondMenuExt;

import java.util.List;

public interface ISecondMenuService extends IService<SecondMenu> {
    List<SecondMenuExt> selectAll();
}
