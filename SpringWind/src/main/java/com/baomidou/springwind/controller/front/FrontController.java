package com.baomidou.springwind.controller.front;

import com.baomidou.kisso.annotation.Permission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/home")
public class FrontController {
    @RequestMapping("/toIndex")
    public String toFrontIndex(){
        return "/front/index";
    }
}
