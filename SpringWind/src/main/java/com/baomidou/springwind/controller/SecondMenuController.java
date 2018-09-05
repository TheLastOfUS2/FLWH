package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.SecondMenu;
import com.baomidou.springwind.entity.ext.SecondMenuExt;
import com.baomidou.springwind.service.ISecondMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    private ISecondMenuService secondMenuService;


	@Permission("1003")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/secondMenu/list";
	}

    @ResponseBody
    @Permission("1003")
    @RequestMapping("/getSecondMenuList")
    public String getUserList() {
        Page<SecondMenu> page = getPage();
        page = secondMenuService.selectPage(page, null);
        List<SecondMenuExt> secondMenuExts = secondMenuService.selectAll();
        Page<SecondMenuExt> secondMenuExtPage = getPage();
        secondMenuExtPage.setRecords(secondMenuExts);
        secondMenuExtPage.setCondition(page.getCondition());
        return jsonPage(secondMenuExtPage);
    }
}
