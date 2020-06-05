package com.jinxin.platform.apcos.cockpit.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-19 16:21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceReport {
    private String serialNum;
    private String deviceName;
    private String deviceMac;
    private String productCode;
    private String productCodeName;
    private Date reportTime;
    private String reportStrTime;
    private String cmdName;
    private String modelId;
}
