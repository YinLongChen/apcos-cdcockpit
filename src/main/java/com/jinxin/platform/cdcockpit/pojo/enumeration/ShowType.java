package com.jinxin.platform.cdcockpit.pojo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Huang LingSong
 *
 * 2020-04-28 9:53
 */
@Slf4j
public enum ShowType {

    SHOW(0, "展示"),

    NOT_SHOW(1, "不展示");

    private final Integer value;
    private final String desc;

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    ShowType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
