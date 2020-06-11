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
    private String deviceMac;
    private String productCode;
    private Date reportTime;
    private String reportStrTime;
    private String modelId;
    private String deviceName;
    private String cmdName;
    private String position;
    private String state;
    private String spare;
    private String productCodeName;

}
