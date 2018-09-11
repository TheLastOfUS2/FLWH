package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Image;
import com.baomidou.springwind.entity.ServiceItem;
import com.baomidou.springwind.service.ImageService;
import com.baomidou.springwind.service.ServiceItemService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sys/serviceItem")
public class ServiceItemController extends BaseController {
    @Autowired
    private ServiceItemService serviceItemService;
    @Autowired
    private ImageService imageService;

    ServiceItem serviceItem1 = new ServiceItem();

    //跳转list
    @Permission("1004")
    @RequestMapping("/list")
    public String serviceItemPage() {
        return "/serviceItem/list";
    }

    //list取信息
    @ResponseBody
    @Permission("1004")
    @RequestMapping("/getServiceItemList")
    public String getServiceItemList() {
        Page<ServiceItem> serviceItemPage = getPage();
        return jsonPage(serviceItemService.selectPage(serviceItemPage, null));
    }

    //跳转添加
    @Permission("1004")
    @RequestMapping("/add")
    public String add(Model model, String id) {
        return "/serviceItem/add";
    }

    //修改查询信息
    @Permission("1004")
    @RequestMapping("/edit")
    public String edit(Model model, String id) {
        if (id != null) {
            model.addAttribute("serviceItem", serviceItemService.selectByServiceItemId(id));
        }
        return "/serviceItem/edit";
    }

    //修改添加
    @ResponseBody
    @Permission("1004")
    @RequestMapping(value = "/editServiceItem", method = RequestMethod.POST)
    public ServiceItem editServiceItem(ServiceItem serviceItem) {
        if (serviceItem != null && serviceItem.getServiceItemId().equals("")) {
            serviceItem.setServiceItemId(UUID.randomUUID().toString());
            serviceItem1.setServiceItemId(serviceItem.getServiceItemId());
            serviceItemService.insert(serviceItem);
        } else {
            serviceItem1.setServiceItemId(serviceItem.getServiceItemId());
            serviceItemService.updateById(serviceItem);
        }
        return serviceItem;
    }

    //图片上传
    @ResponseBody
    @Permission("1004")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadReport(HttpServletRequest request, HttpServletResponse response) {
        ServiceItem serviceItem = new ServiceItem();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        String s = uploadFile(request, multipartHttpServletRequest.getParameter("name"));
        serviceItem.setServiceItemId(serviceItem1.getServiceItemId());
        if (multipartHttpServletRequest.getParameter("name").equals("titleImage")) {
            serviceItem.setTitleImage("\\static\\image\\" + s);
        } else {
            serviceItem.setDescribeImage("\\static\\image\\" + s);
        }
        serviceItemService.updateById(serviceItem);
        return "1";
    }

    //图片上传image
    @ResponseBody
    @Permission("1004")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public String uploadImage(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        uploadImage(request, multipartHttpServletRequest.getParameter("name"), serviceItem1.getServiceItemId());
        return "1";
    }

    /**
     * 上传图片
     */
    private String uploadFile(HttpServletRequest request, String dstFileName) {
        //判断保存文件的路径是否存在
        File fileUploadPath = new File("C:\\Users\\18502\\Desktop\\zar\\java\\FLWH\\SpringWind\\src\\main\\webapp\\WEB-INF\\static\\image");
        String saveFileName = "";    //保存到服务器目录的文件名称
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdir();
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles(dstFileName);
            for (MultipartFile item : fileList) {
                String fileName = "";        //当前上传文件全名称
                try {
                    fileName = item.getOriginalFilename();
                    saveFileName = getNowDate() + "_" + getRandomString(16) + ".jpg";
                    File savedFile = new File(fileUploadPath, saveFileName);
                    item.transferTo(savedFile);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return saveFileName;
    }

    /**
     * 上传图片image
     */
    private void uploadImage(HttpServletRequest request, String dstFileName, String serviceItemId) {
        //判断保存文件的路径是否存在
        File fileUploadPath = new File("C:\\Users\\18502\\Desktop\\zar\\java\\FLWH\\SpringWind\\src\\main\\webapp\\WEB-INF\\static\\image");
        String saveFileName = "";    //保存到服务器目录的文件名称
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdir();
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            Image image = new Image();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles(dstFileName);
            for (MultipartFile item : fileList) {
                String fileName = "";        //当前上传文件全名称
                try {
                    fileName = item.getOriginalFilename();
                    saveFileName = getNowDate() + "_" + getRandomString(16) + ".jpg";
                    File savedFile = new File(fileUploadPath, saveFileName);
                    item.transferTo(savedFile);
                    image.setImageId(UUID.randomUUID().toString());
                    image.setImageUrl("\\static\\image\\" + saveFileName);
                    image.setItemId(serviceItemId);
                    imageService.insert(image);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /*
     * 获取当前时间
     */
    private String getNowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());
    }

    /*
     * 生成文件名
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
