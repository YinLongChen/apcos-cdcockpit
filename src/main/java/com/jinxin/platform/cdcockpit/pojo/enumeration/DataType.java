package com.jinxin.platform.cdcockpit.pojo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Huang LingSong
 * 2020-04-28 9:53
 */
@Slf4j
public enum DataType {

    DATE(0, "日期类型"),

    DATESTR(2, "日期字符串"),

    OTHER(1, "其他类型");

    private final Integer value;
    private final String desc;

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    DataType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
