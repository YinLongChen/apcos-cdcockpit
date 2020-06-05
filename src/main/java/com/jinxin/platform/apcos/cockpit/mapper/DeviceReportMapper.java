package com.jinxin.platform.apcos.cockpit.mapper;

import com.jinxin.platform.apcos.cockpit.pojo.domain.DeviceReport;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.ReportCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 设备上报
 *
 * @author Huang LingSong
 */
@Mapper
public interface DeviceReportMapper {

    /**
     * 查询业务类型
     *
     * @return
     */
    @Select("select distinct CMD_NAME from ODS_DEVICE_REPORT")
    List<String> selectReportType();

    List<DeviceReport> selectReport(ReportCriteria reportCriteria);
}
