package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.SecondMenu;

public interface SecondMenuDao {
    int deleteByPrimaryKey(String secondMenuId);

    int insert(SecondMenu record);

    int insertSelective(SecondMenu record);

    SecondMenu selectByPrimaryKey(String secondMenuId);

    int updateByPrimaryKeySelective(SecondMenu record);

    int updateByPrimaryKey(SecondMenu record);
}