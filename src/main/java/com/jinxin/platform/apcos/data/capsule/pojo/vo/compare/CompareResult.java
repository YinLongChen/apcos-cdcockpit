package com.jinxin.platform.apcos.data.capsule.pojo.vo.compare;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-05-28 14:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompareResult {


    /**
     * 基点时间
     */
    private String time;

    /**
     * 总量
     */
    private Integer total;

    /**
     * 数量
     */
    private Integer value;

    /**
     * 同比
     */
    private String yearOnYear;

    /**
     * 环比
     */
    private String linkRatio;
    /**
     * 数量单位
     */
    private String unit;
}
