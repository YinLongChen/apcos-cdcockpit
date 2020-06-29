package com.jinxin.platform.cdcockpit.pojo.enumeration;

public enum MonitorStatus {
    ONLINE(1, "在线"),

    OFFLINE(0, "离线");

    private final Integer value;
    private final String desc;

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    MonitorStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
