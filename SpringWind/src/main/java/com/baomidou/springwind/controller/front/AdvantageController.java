package com.baomidou.springwind.controller.front;

import com.baomidou.kisso.annotation.Permission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/advantage")
public class AdvantageController {
    @RequestMapping("/toAdvantage")
    public String toAdvantage(){
        return "/front/fenglanyoushi";
    }
}