package com.jinxin.platform.cdcockpit.pojo.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Huang LingSong
 * 2020-04-28 9:53
 */
@Slf4j
public enum GenderType {

    MAN("0", "女"),

    WOMAN("1", "男"),

    OTHER("other", "其他");

    private final String value;
    private final String desc;

    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    GenderType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static GenderType getByVal(String value) {
        value = value == null ? "" : value;
        for (GenderType v : GenderType.values()) {
            if (v.getValue().equals(value + "")) {
                return v;
            }
        }
        log.error("参数错误，找不到对应值:{}", value);
        return OTHER;
    }
    public static GenderType getByDesc(String desc){
        for (GenderType v : GenderType.values()){
            if (v.getDesc().equals(desc + "")){
                return v;
            }
        }
        log.error("参数错误，找不到对应值:{}", desc);
        return OTHER;
    }
}
