package com.jinxin.platform.apcos.cockpit.mapper;

import com.jinxin.platform.apcos.cockpit.pojo.domain.DeviceReport;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ReportOperation;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.ReportCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    @Select("select distinct CMD_NAME from V_ODS_DEVICE_REPORT")
    List<String> selectReportType();

    List<DeviceReport> selectReport(ReportCriteria reportCriteria);

    /**
     * 查询数据操作
     * @return
     */
    @Select("SELECT ID,NAME,URL,METHOD,PARAM FROM V_ODS_DEVICE_REPORT_OPERATION")
    List<ReportOperation> selectOpreation();

    /**
     * 查询每个设备的最新上报记录(当同一设备上报时间相同的会有重复记录)
     * @param reportCriteria
     * @return
     */
    List<DeviceReport> selectMaxTimeReport(ReportCriteria reportCriteria);
}
