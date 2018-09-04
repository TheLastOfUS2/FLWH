package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.ServiceItem;

public interface ServiceItemDao {
    int deleteByPrimaryKey(String serviceItemId);

    int insert(ServiceItem record);

    int insertSelective(ServiceItem record);

    ServiceItem selectByPrimaryKey(String serviceItemId);

    int updateByPrimaryKeySelective(ServiceItem record);

    int updateByPrimaryKey(ServiceItem record);
}