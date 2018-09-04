package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 邮件发送接收
 * </p>
 *
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/sys/secondMenu")
public class SecondMenuController extends BaseController {

	@Permission("1003")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/secondMenu/list";
	}

}
