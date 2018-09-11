package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.SecondMenu;
import com.baomidou.springwind.entity.ext.SecondMenuExt;

import java.util.List;

public interface SecondMenuDao extends BaseMapper<SecondMenu> {
    int deleteByPrimaryKey(String secondMenuId);

    int insertSelective(SecondMenu record);

    SecondMenu selectByPrimaryKey(String secondMenuId);

    int updateByPrimaryKeySelective(SecondMenu record);

    int updateByPrimaryKey(SecondMenu record);

    List<SecondMenuExt> selectAll();

    SecondMenuExt selectById(String secondMenuId);
}