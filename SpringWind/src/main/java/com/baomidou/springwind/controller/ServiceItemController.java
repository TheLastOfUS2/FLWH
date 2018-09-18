package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.ServiceItem;
import com.baomidou.springwind.service.IServiceItemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;


@Controller
@RequestMapping("/sys/serviceItem")
public class ServiceItemController extends BaseController {

    @Autowired
    private IServiceItemService serviceItemService;


	@Permission("1005")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/serviceItem/list";
	}

    @ResponseBody
    @Permission("1005")
    @RequestMapping("/getServiceItemList")
    public String getServiceItemList() {
        Page<ServiceItem> page = getPage();
        EntityWrapper ew=new EntityWrapper();
        ew.setEntity(new ServiceItem());
        ew.orderBy("insertTime",false);
        return jsonPage(serviceItemService.selectPage(page, ew));
    }

    @ResponseBody
    @Login(action = Action.Skip)
    @Permission(action = Action.Skip)
    @RequestMapping("/getServiceItemListForFront")
    public String getServiceItemListForFront(String serviceType) {
        Page<ServiceItem> page = getPage();
        EntityWrapper ew=new EntityWrapper();
        ew.setEntity(new ServiceItem());
        if (!StringUtils.isEmpty(serviceType)){
            ew.eq("serviceType",serviceType);
        }
        ew.orderBy("insertTime",false);
        return jsonPage(serviceItemService.selectPage(page, ew));
    }

    @ResponseBody
    @Login(action = Action.Skip)
    @Permission(action = Action.Skip)
    @RequestMapping("/getServiceItemForFront")
    public JSONObject getServiceItemForFront(String serviceItemId) {
        ServiceItem serviceItem = serviceItemService.selectById(serviceItemId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",serviceItem);
        return jsonObject;
    }

    @Permission("1005")
    @RequestMapping("/edit")
    public String edit(Model model, String id) {
        if ( id != null ) {
            model.addAttribute("serviceItem", serviceItemService.selectById(id));
        }
        return "/serviceItem/edit";
    }

    @ResponseBody
    @Permission("1005")
    @RequestMapping("/delServiceItem/{id}")
    public String delServiceItem(@PathVariable String id) {
        serviceItemService.deleteById(id);
        return Boolean.TRUE.toString();
    }

    @Permission("1005")
    @ResponseBody
    @RequestMapping("/editServiceItem")
    public String editServiceItem(ServiceItem serviceItem) {
        boolean rlt = false;
        if ( serviceItem != null ) {
            if ( serviceItem.getServiceItemId() != null && !"".equals(serviceItem.getServiceItemId())) {
                rlt = serviceItemService.updateById(serviceItem);
            }else {
                serviceItem.setInsertTime(new Date());
                serviceItem.setServiceItemId(UUID.randomUUID().toString());
                rlt = serviceItemService.insert(serviceItem);
            }
        }
        return callbackSuccess(rlt);
    }
}
