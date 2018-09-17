package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Image;
import com.baomidou.springwind.entity.ServiceItem;
import com.baomidou.springwind.service.ImageService;
import com.baomidou.springwind.service.ServiceItemService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    private ServiceItem serviceItem1 = new ServiceItem();

    //未添加图片时默认图片路径
    private String defaultUrl = "/static/img/timg.jpg";

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
    public String edit(HttpServletRequest request, Model model, String id) {
        String image = "";
        if (id != null) {
            ServiceItem serviceItem = serviceItemService.selectByServiceItemId(id);
            model.addAttribute("serviceItem", serviceItem);
            image = image + "/SpringWind" + serviceItem.getImageList().get(0).getImageUrl();
            if (serviceItem.getImageList().size() > 1) {
                for (int i = 1; i < serviceItem.getImageList().size(); i++) {
                    image = image + "," + "/SpringWind" + serviceItem.getImageList().get(i).getImageUrl();
                }
            }
            model.addAttribute("image", image);
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

    @ResponseBody
    @Permission("1004")
    @RequestMapping("/delServiceItem/{serviceItemId}")
    public String delServiceItem(HttpServletRequest request, @PathVariable String serviceItemId) {
        ServiceItem serviceItem = serviceItemService.selectByServiceItemId(serviceItemId);
        for (Image i : serviceItem.getImageList()) {
            if (i.getImageUrl().equals(defaultUrl)) {
                break;
            }
            String imageUrl = i.getImageUrl();
            deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF") + imageUrl);
        }
        if (!serviceItem.getTitleImage().equals(defaultUrl)) {
            deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF") + serviceItem.getTitleImage());
        }
        if (!serviceItem.getDescribeImage().equals(defaultUrl)) {
            deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF") + serviceItem.getDescribeImage());
        }
        serviceItemService.deleteById(serviceItemId);
        imageService.deleteByItemId(serviceItemId);
        return Boolean.TRUE.toString();
    }

    //图片上传
    @ResponseBody
    @Permission("1004")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadReport(HttpServletRequest request, HttpServletResponse response) {
        ServiceItem serviceItem = new ServiceItem();
        MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
        String s = uploadFile(request, servletRequest.getParameter("name"));
        serviceItem.setServiceItemId(serviceItem1.getServiceItemId());
        if (servletRequest.getParameter("name").equals("titleImage")) {
            serviceItem.setTitleImage(s);
        } else {
            serviceItem.setDescribeImage(s);
        }
        serviceItemService.updateById(serviceItem);
        return "1";
    }

    @ResponseBody
    @Permission("1004")
    @RequestMapping(value = "/editTitleImage", method = RequestMethod.POST)
    public String editTitleImage(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
        File fileUploadPath = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/static/img"));
        ServiceItem serviceItem = serviceItemService.selectByServiceItemId(servletRequest.getParameter("serviceItemId"));
        String saveFileName = "";    //保存到服务器目录的文件名称
        if (ServletFileUpload.isMultipartContent(request)) {
            List<MultipartFile> fileList = servletRequest.getFiles(servletRequest.getParameter("name"));
            if (fileList.size() == 0  &&  !serviceItem.getTitleImage().equals(defaultUrl)) {
                return "1";
            } else if (fileList.size() == 0) {
                saveFileName = defaultUrl;
                if (!serviceItem.getTitleImage().equals(defaultUrl)) {
                    deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF") + serviceItem.getTitleImage());
                }
            } else {
                for (MultipartFile item : fileList) {
                    try {
                        saveFileName = getNowDate() + "_" + getRandomString(16) + ".jpg";
                        File savedFile = new File(fileUploadPath, saveFileName);
                        item.transferTo(savedFile);
                        saveFileName = "/static/img/" + saveFileName;
                        if (!serviceItem.getTitleImage().equals(defaultUrl)) {
                            deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF") + serviceItem.getTitleImage());
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        serviceItem.setTitleImage(saveFileName);
        serviceItemService.updateById(serviceItem);
        return "1";
    }

    @ResponseBody
    @Permission("1004")
    @RequestMapping(value = "/editDescribeImage", method = RequestMethod.POST)
    public String editDescribeImage(HttpServletRequest request, HttpServletResponse response) {
        String saveFileName = "";    //保存到服务器目录的文件名称
        MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
        File fileUploadPath = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/static/img"));
        ServiceItem serviceItem = serviceItemService.selectByServiceItemId(servletRequest.getParameter("serviceItemId"));
        if (ServletFileUpload.isMultipartContent(request)) {
            List<MultipartFile> fileList = servletRequest.getFiles(servletRequest.getParameter("name"));
             if (fileList.size() == 0  &&  !serviceItem.getDescribeImage().equals(defaultUrl)) {
                return "1";
            } else if (fileList.size() == 0) {
                saveFileName = defaultUrl;
                if (!serviceItem.getDescribeImage().equals(defaultUrl)) {
                    deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF") + serviceItem.getDescribeImage());
                }
            } else {
                for (MultipartFile item : fileList) {
                    try {
                        saveFileName = getNowDate() + "_" + getRandomString(16) + ".jpg";
                        File savedFile = new File(fileUploadPath, saveFileName);
                        item.transferTo(savedFile);
                        saveFileName = "/static/img/" + saveFileName;
                        if (!serviceItem.getDescribeImage().equals(defaultUrl)) {
                            deleteFile(request.getSession().getServletContext().getRealPath("/WEB-INF") + serviceItem.getDescribeImage());
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        serviceItem.setDescribeImage(saveFileName);
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
        File fileUploadPath = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/static/img"));
        String saveFileName = "";    //保存到服务器目录的文件名称
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdir();
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles(dstFileName);
            if (fileList.size() == 0) {
                saveFileName = defaultUrl;
            } else {
                for (MultipartFile item : fileList) {
                    try {
                        saveFileName = getNowDate() + "_" + getRandomString(16) + ".jpg";
                        File savedFile = new File(fileUploadPath, saveFileName);
                        item.transferTo(savedFile);
                        saveFileName = "/static/img/" + saveFileName;
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
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
        File fileUploadPath = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/static/img"));
        String saveFileName = "";    //保存到服务器目录的文件名称
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdir();
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            Image image = new Image();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles(dstFileName);
            if (fileList.size() == 0) {
                saveFileName = defaultUrl;
                image.setImageId(UUID.randomUUID().toString());
                image.setImageUrl(saveFileName);
                image.setItemId(serviceItemId);
                imageService.insert(image);
            } else {
                for (MultipartFile item : fileList) {
                    try {
                        saveFileName = getNowDate() + "_" + getRandomString(16) + ".jpg";
                        File savedFile = new File(fileUploadPath, saveFileName);
                        item.transferTo(savedFile);
                        image.setImageId(UUID.randomUUID().toString());
                        image.setImageUrl("/static/img/" + saveFileName);
                        image.setItemId(serviceItemId);
                        imageService.insert(image);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
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
    private String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //删除文件
    private void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            file.delete();
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
        }
    }
}
