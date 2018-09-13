package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.NewsItem;
import com.baomidou.springwind.entity.SecondMenu;
import com.baomidou.springwind.entity.ext.SecondMenuExt;
import com.baomidou.springwind.service.INewsItemService;
import com.baomidou.springwind.service.ISecondMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/sys/newsItem")
public class NewsItemController extends BaseController {

    @Autowired
    private INewsItemService newsItemService;


	@Permission("1005")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/newsItem/list";
	}

    @ResponseBody
    @Permission("1005")
    @RequestMapping("/getNewsItemList")
    public String getNewsItemList() {
        Page<NewsItem> page = getPage();
        return jsonPage(newsItemService.selectPage(page, null));
    }

    @Permission("1005")
    @RequestMapping("/edit")
    public String edit(Model model, String id) {
        if ( id != null ) {
            model.addAttribute("newsItem", newsItemService.selectById(id));
        }
        return "/newsItem/edit";
    }

    @ResponseBody
    @Permission("1005")
    @RequestMapping("/delNewsItem/{id}")
    public String delNewsItem(@PathVariable String id) {
        newsItemService.deleteById(id);
        return Boolean.TRUE.toString();
    }

    @Permission("1005")
    @ResponseBody
    @RequestMapping("/editNewsItem")
    public String editNewsItem(NewsItem newsItem) {
        boolean rlt = false;
        if ( newsItem != null ) {
            if ( newsItem.getNewsItemId() != null && !"".equals(newsItem.getNewsItemId())) {
                rlt = newsItemService.updateById(newsItem);
            }else {
                newsItem.setInsertTime(new Date());
                newsItem.setNewsItemId(UUID.randomUUID().toString());
                rlt = newsItemService.insert(newsItem);
            }
        }
        return callbackSuccess(rlt);
    }
}
