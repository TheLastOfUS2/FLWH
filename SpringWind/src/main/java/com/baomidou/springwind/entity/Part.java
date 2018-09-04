package com.baomidou.springwind.entity;

public class Part {
    private String partId;

    private String partValue;

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId == null ? null : partId.trim();
    }

    public String getPartValue() {
        return partValue;
    }

    public void setPartValue(String partValue) {
        this.partValue = partValue == null ? null : partValue.trim();
    }
}