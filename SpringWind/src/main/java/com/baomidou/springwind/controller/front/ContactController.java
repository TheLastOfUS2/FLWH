package com.baomidou.springwind.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/contact")
public class ContactController {
    @RequestMapping("/toContact")
    public String toAbout(){
        return "/front/lianxiwomen";
    }
}