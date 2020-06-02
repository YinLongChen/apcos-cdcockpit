package com.jinxin.platform.apcos.data.capsule.pojo.domain;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-05-28 11:00
 */
@Data
public class CompareModel {
    private String id;
    private String modelId;
    /**
     * 数量
     */
    private Integer value;
    /**
     * 月份
     */
    private String month;
    /**
     * 数量单位
     */
    private String unit;
}
