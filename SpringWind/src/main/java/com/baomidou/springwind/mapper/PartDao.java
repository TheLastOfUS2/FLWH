package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.Part;

public interface PartDao {
    int deleteByPrimaryKey(String partId);

    int insert(Part record);

    int insertSelective(Part record);

    Part selectByPrimaryKey(String partId);

    int updateByPrimaryKeySelective(Part record);

    int updateByPrimaryKey(Part record);
}