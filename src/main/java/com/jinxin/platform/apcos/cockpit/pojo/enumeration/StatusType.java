package com.jinxin.platform.apcos.cockpit.pojo.enumeration;

public enum StatusType {
    DRAFT(0, "草稿"),

    RELEASE(1, "发布");

    private final Integer value;
    private final String desc;

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    StatusType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
