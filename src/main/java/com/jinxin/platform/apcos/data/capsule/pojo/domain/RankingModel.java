package com.jinxin.platform.apcos.data.capsule.pojo.domain;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-05-15 14:29
 */
@Data
public class RankingModel {
    private String id;
    private String name;
    private Integer value;
    private Integer top;
    private String modelId;
}
