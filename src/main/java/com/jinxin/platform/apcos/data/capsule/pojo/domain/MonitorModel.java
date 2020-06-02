package com.jinxin.platform.apcos.data.capsule.pojo.domain;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-05-29 10:17
 */
@Data
public class MonitorModel {
    private String id;
    private String modelId;
    private String deviceId;
    private String deviceName;
    private String position;
    private String status;
    private String rtmpUrl;
}
