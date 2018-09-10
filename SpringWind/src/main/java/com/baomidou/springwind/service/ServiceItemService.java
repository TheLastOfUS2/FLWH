package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.ServiceItem;

/**
 * @author zar on 2018/9/5
 */
public interface ServiceItemService extends IService<ServiceItem> {
    ServiceItem selectByServiceItemId(String id);
}
