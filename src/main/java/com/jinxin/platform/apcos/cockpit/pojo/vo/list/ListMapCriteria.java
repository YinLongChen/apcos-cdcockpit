package com.jinxin.platform.apcos.cockpit.pojo.vo.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-06-22 11:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListMapCriteria {
    private String modelId;
    private Integer show;
}
