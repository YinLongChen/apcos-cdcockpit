package com.jinxin.platform.cdcockpit.pojo.domain;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-05-29 10:17
 */
@Data
public class MonitorModel {
    private String id;
    private String deviceName;
    private String deviceNum;
    private String position;
    private Integer status;
    private String rtmp;
    private String rtsp;

}
