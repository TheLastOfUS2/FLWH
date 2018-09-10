package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.Image;
import com.baomidou.springwind.mapper.ImageDao;
import com.baomidou.springwind.service.ImageService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends BaseServiceImpl<ImageDao, Image> implements ImageService {
}
