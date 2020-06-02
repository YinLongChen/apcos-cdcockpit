package com.jinxin.platform.apcos.data.capsule.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-06-01 16:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LayoutCriteria {
    private String id;
    private String userId;
    private Integer status;
}
