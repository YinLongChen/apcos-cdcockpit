package com.jinxin.platform.cdcockpit.pojo.vo.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-15 10:01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCriteria {
    private String productCode;
    private Date startTime;
}
