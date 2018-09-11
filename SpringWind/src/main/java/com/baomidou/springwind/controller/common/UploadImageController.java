package com.baomidou.springwind.controller.common;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/sys/common")
public class UploadImageController {

    @Login(action = Action.Skip)
    @Permission(action = Action.Skip)
    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject updatePhoto(HttpServletRequest request, HttpServletResponse response, @RequestParam("myFile") MultipartFile myFile) {
        JSONObject json = new JSONObject();
        try {
            //输出文件后缀名称
            System.out.println(myFile.getOriginalFilename());
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //图片名称
            String name = df.format(new Date());

            Random r = new Random();
            for (int i = 0; i < 3; i++) {
                name += r.nextInt(10);
            }
            //
            String ext = FilenameUtils.getExtension(myFile.getOriginalFilename());
            //保存图片       File位置 （全路径）   /upload/fileName.jpg
            String url = request.getSession().getServletContext().getRealPath("/WEB-INF/static/img/");
            //相对路径
            String path = "/" + name + "." + ext;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            myFile.transferTo(new File(url + path));
            json.put("success", "/SpringWind/static/img" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
