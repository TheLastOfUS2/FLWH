package com.baomidou.springwind.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/news")
public class NewsController {
    @RequestMapping("/toNews")
    public String toNews(){
        return "/front/dongtainei";
    }
}