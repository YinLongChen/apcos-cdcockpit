package com.jinxin.platform.apcos.cockpit.pojo.vo.list;

import lombok.Data;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-18 17:27
 */
@Data
public class CubeForm {
    private String modelId;

    /**
     * 当X轴为时间类型时必传，3-周统计，2-月统计，1-年统计
     */
    private Integer field;
    /**
     * X轴
     */
    private String xAxis;
    /**
     * 维度
     */
    private String cube;

    /**
     * 过滤数据
     */
    private List<Where> where;
}





