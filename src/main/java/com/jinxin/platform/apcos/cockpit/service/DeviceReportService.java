package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountResult;

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
}
