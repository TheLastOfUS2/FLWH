package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("second_menu")
public class SecondMenu {
    @TableId
    private String secondMenuId;

    private String titleOne;

    private String titleTwo;

    private String titleThree;

    private String describeOne;

    private String describeTwo;

    private String describeThree;

    private String imageId;

    private String partId;

    public String getSecondMenuId() {
        return secondMenuId;
    }

    public void setSecondMenuId(String secondMenuId) {
        this.secondMenuId = secondMenuId == null ? null : secondMenuId.trim();
    }

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne == null ? null : titleOne.trim();
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo == null ? null : titleTwo.trim();
    }

    public String getTitleThree() {
        return titleThree;
    }

    public void setTitleThree(String titleThree) {
        this.titleThree = titleThree == null ? null : titleThree.trim();
    }

    public String getDescribeOne() {
        return describeOne;
    }

    public void setDescribeOne(String describeOne) {
        this.describeOne = describeOne == null ? null : describeOne.trim();
    }

    public String getDescribeTwo() {
        return describeTwo;
    }

    public void setDescribeTwo(String describeTwo) {
        this.describeTwo = describeTwo == null ? null : describeTwo.trim();
    }

    public String getDescribeThree() {
        return describeThree;
    }

    public void setDescribeThree(String describeThree) {
        this.describeThree = describeThree == null ? null : describeThree.trim();
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId == null ? null : imageId.trim();
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId == null ? null : partId.trim();
    }
}