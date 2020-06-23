package com.jinxin.platform.apcos.cockpit.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-06-22 16:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountStrResult {
    private String name;
    private String value;
    private String remark;
}
