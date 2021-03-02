package com.jinxin.platform.cdcockpit.pojo.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    private List<DeviceReportData> datas;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceReport that = (DeviceReport) o;
        return serialNum.equals(that.serialNum) &&
                reportTime.equals(that.reportTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNum, reportTime);
    }
}
