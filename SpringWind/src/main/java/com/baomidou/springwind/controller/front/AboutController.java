package com.baomidou.springwind.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/about")
public class AboutController {
    @RequestMapping("/toAbout")
    public String toAbout(){
        return "/front/guanyufenglan";
    }
}