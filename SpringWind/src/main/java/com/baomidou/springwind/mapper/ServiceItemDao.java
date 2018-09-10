package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.ServiceItem;

public interface ServiceItemDao extends BaseMapper<ServiceItem> {
    /**
     * 查找业务信息级联图片
     * @param id
     * @return
     */
    ServiceItem selectByServiceItemId(String id);
}