package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.ServiceItem;
import com.baomidou.springwind.service.ServiceItemService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/serviceItem")
public class ServiceItemController extends BaseController {
    @Autowired
    private ServiceItemService serviceItemService;

    @Permission("1004")
    @RequestMapping("/list")
    public String serviceItemPage() {
        return "/serviceItem/list";
    }

    @ResponseBody
    @Permission("1004")
    @RequestMapping("/getServiceItemList")
    public String getServiceItemList() {
        Page<ServiceItem> serviceItemPage = getPage();
        return jsonPage(serviceItemService.selectPage(serviceItemPage, null));
    }

    @Permission("1004")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {

        System.out.println(model);
        if (id != null) {
            model.addAttribute("serviceItem", serviceItemService.selectById(id));
        }
//        model.addAttribute("roleList", roleService.selectList(null));
        return "/serviceItem/edit";
    }

    @ResponseBody
    @Permission("1004")
    @RequestMapping(value = "/editServiceItem",method = RequestMethod.POST)
    public String editServiceItem(ServiceItem serviceItem) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!" + serviceItem);
        if (serviceItem != null && serviceItem.getServiceItemId() == 0) {
            serviceItemService.insert(serviceItem);
        } else {
            serviceItemService.updateById(serviceItem);
        }
        return "1";
    }

    /**
     * 报告上传
     */
    @Permission("1004")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadReport(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        //获取参数
        String reportGroupId = (String) request.getSession().getAttribute("titleImage");

        //调用通用接口上传文件
        result = uploadFile(request, "titleImage", "titleImage");

        return callbackSuccess(result);
    }

    /**
     * 上传文件通用接口
     *
     * @param request       请求体
     * @param dstFileName   html上传组件中(input中name属性)，上传文件体名称，通过此名称获取所有上传的文件map
     * @param reportGroupId （特殊）上传报告所述报告组id
     */
    protected Map<String, Object> uploadFile(HttpServletRequest request, String dstFileName, String reportGroupId) {
        Map<String, Object> ret = new HashMap<String, Object>();

        //判断保存文件的路径是否存在
        File fileUploadPath = new File("C:\\Users\\18502\\Desktop\\zar\\java\\FLWH\\SpringWind\\src\\main\\webapp\\WEB-INF\\image");
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdir();
        }

        if (ServletFileUpload.isMultipartContent(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles(dstFileName);
            for (MultipartFile item : fileList) {
                String fileName = "";        //当前上传文件全名称
                String fileType = "";        //当前上传文件类型
                String saveFileName = "";    //保存到服务器目录的文件名称
                String reportAddr = "";      //保存到服务器目录的文件全路径
                try {
                    fileName = item.getOriginalFilename();
                    fileType = item.getContentType();
                    saveFileName = "111_" + fileName;
                    reportAddr = fileUploadPath + "/" + saveFileName;
                    reportAddr = reportAddr.replace("/", File.separator).replace("\\", File.separator);

                    File savedFile = new File(fileUploadPath, saveFileName);
                    item.transferTo(savedFile);

//                    //上传文件成功，保存文件信息到表reportDetail
//                    Map<String, Object> param = new HashMap<String, Object>();
//                    param.put("reportGroupId", reportGroupId);
//                    param.put("reportName", fileName);
//                    param.put("reportType", fileType);
//                    param.put("reportAddr", reportAddr);
//                    param.put("createTime", getCurrentTime("yyyy-MM-dd HH:mm:ss"));
//                    param.put("lastOper", "xxxxxxx");
//                    ApiResponse r = (ApiResponse) saveEntity("/report/saveReportDetail", param, ApiResponse.class);

//                    if (r.getCode() == 0) {
//                        ret.put("success", true);
//                    } else {
//                        ret.put("success", false);
//                        ret.put("message", r.getMessage());
//                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    ret.put("success", false);
                    ret.put("message", e.getMessage());
                }
            }
        }
        return ret;
    }
}
