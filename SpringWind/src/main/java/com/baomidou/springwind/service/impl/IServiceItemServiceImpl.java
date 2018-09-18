package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.ServiceItem;
import com.baomidou.springwind.entity.ext.SecondMenuExt;
import com.baomidou.springwind.mapper.ServiceItemDao;
import com.baomidou.springwind.service.IServiceItemService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IServiceItemServiceImpl extends BaseServiceImpl<ServiceItemDao,ServiceItem> implements IServiceItemService {

}
