package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.ServiceItem;
import com.baomidou.springwind.mapper.ServiceItemDao;
import com.baomidou.springwind.service.ServiceItemService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceItemServiceImpl extends BaseServiceImpl<ServiceItemDao,ServiceItem> implements ServiceItemService {

}
