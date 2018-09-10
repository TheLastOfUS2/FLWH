package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;
import java.util.List;

@TableName("service_item")
public class ServiceItem {
    @TableId
    private String serviceItemId;

    private String titleImage;

    private String titleText;

    private String describeText;

    private String describeImage;

    private String serviceType;

    private String insertTime;

    @TableField(exist = false)
    private List<Image> imageList;

    public String getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(String serviceItemId) {
        this.serviceItemId = serviceItemId == null ? null : serviceItemId.trim();
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage == null ? null : titleImage.trim();
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText == null ? null : titleText.trim();
    }

    public String getDescribeText() {
        return describeText;
    }

    public void setDescribeText(String describeText) {
        this.describeText = describeText == null ? null : describeText.trim();
    }

    public String getDescribeImage() {
        return describeImage;
    }

    public void setDescribeImage(String describeImage) {
        this.describeImage = describeImage == null ? null : describeImage.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime == null ? null : insertTime.trim();
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "ServiceItem{" +
                "serviceItemId='" + serviceItemId + '\'' +
                ", titleImage='" + titleImage + '\'' +
                ", titleText='" + titleText + '\'' +
                ", describeText='" + describeText + '\'' +
                ", describeImage='" + describeImage + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", insertTime='" + insertTime + '\'' +
                '}';
    }
}