package com.jinxin.platform.apcos.data.capsule.mapper;

import com.jinxin.platform.apcos.data.capsule.pojo.domain.DeviceReport;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.device.ReportCriteria;
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
