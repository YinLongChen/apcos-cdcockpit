package com.jinxin.platform.apcos.data.capsule.pojo.vo.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-19 16:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportCriteria {
    private String produceCode;
    private Date startTime;
    private String modelId;
    private String cmdName;
}
