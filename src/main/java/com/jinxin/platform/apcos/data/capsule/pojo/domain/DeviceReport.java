package com.jinxin.platform.apcos.data.capsule.pojo.domain;

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
    private String deviceNum;
    private String deviceName;
    private String deviceMac;
    private String produceCode;
    private Date reportTime;
    private String reportStrTime;
    private String cmdName;
    private String modelId;
}
