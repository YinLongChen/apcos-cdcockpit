package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.domain.DeviceReport;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ReportOperation;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.ReportCriteria;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-19 16:25
 */
public interface DeviceReportService {

    /**
     * 获取具体业务类型
     *
     * @return
     */
    List<String> findReportType();

    /**
     * 最近一天/一周/一月/一年设备上报统计
     * @param field 5-天统计，3-周统计，2-月统计，1-年统计
     * @param type  上报类型
     * @return
     */
    List<CountResult> reportCountByTime(int field,String type);

    /**
     * 设备上报类型统计
     * @param field 5-天统计，3-周统计，2-月统计，1-年统计
     * @return
     */
    List<CountResult> reportCountByType(int field);

    /**
     * 根据业务类型获取数据表格操作
     * @return
     */
    List<ReportOperation> findOperation();

    /**
     * 查询上报数据列表
     * @param criteria
     * @return
     */
    List<DeviceReport> findReport(ReportCriteria criteria);

    /**
     * 查询每个设备的最新上报记录
     * @param criteria
     * @return
     */
    List<DeviceReport> findMaxTimeReport(ReportCriteria criteria);
}
