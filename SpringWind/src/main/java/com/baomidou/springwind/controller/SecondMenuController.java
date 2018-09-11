package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Image;
import com.baomidou.springwind.entity.SecondMenu;
import com.baomidou.springwind.entity.ext.SecondMenuExt;
import com.baomidou.springwind.service.ISecondMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
    public String getSecondMenuList() {
        Page<SecondMenu> page = getPage();
        page = secondMenuService.selectPage(page, null);
        List<SecondMenuExt> secondMenuExts = secondMenuService.selectAll();
        Page<SecondMenuExt> secondMenuExtPage = getPage();
        secondMenuExtPage.setRecords(secondMenuExts);
        secondMenuExtPage.setTotal(page.getTotal());
        secondMenuExtPage.setSize(page.getSize());
        secondMenuExtPage.setCurrent(page.getPages());
        secondMenuExtPage.setCondition(page.getCondition());
        return jsonPage(secondMenuExtPage);
    }

    @Permission("1003")
    @RequestMapping("/edit")
    public String edit(Model model, String id) {
        if ( id != null ) {
            model.addAttribute("secondMenu", secondMenuService.selectByIdMy(id));
        }
        return "/secondMenu/edit";
    }

    @Permission("1003")
    @ResponseBody
    @RequestMapping("/editSecondMenu")
    public String editSecondMenu(SecondMenu secondMenu) {
        boolean rlt = false;
        if ( secondMenu != null ) {
            if ( secondMenu.getSecondMenuId() != null ) {
                rlt = secondMenuService.updateById(secondMenu);
            }
        }
        return callbackSuccess(rlt);
    }
}
