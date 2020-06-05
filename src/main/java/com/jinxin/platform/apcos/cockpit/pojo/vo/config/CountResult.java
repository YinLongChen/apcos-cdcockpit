package com.jinxin.platform.apcos.cockpit.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-05-14 9:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountResult {
    private String name;
    private Integer value;
    private String remark;
}
