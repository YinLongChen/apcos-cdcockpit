package com.jinxin.platform.cdcockpit.pojo.vo.monitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-07-02 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorForm {
    private String id;
    private String deviceName;
    private String position;
    private String rtsp;
}
