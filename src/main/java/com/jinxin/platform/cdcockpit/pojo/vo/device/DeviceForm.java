package com.jinxin.platform.cdcockpit.pojo.vo.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-20 11:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceForm {
    private String productCode;
    private Date startTime;
    private Integer current = 1;
    private Integer size = 10;
}
