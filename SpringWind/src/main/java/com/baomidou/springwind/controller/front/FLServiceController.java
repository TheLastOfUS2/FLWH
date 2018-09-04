package com.baomidou.springwind.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/flService")
public class FLServiceController {
    @RequestMapping("/toFLService")
    public String toFLService(){
        return "/front/anlinei";
    }
}