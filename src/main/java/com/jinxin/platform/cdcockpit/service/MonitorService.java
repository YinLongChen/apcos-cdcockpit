package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.domain.MonitorModel;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-29 9:59
 */
public interface MonitorService {



    /**
     * 获取所有的业务数据
     *
     * @return
     */
    List<MonitorModel> findMonitorAll();

    boolean refreshStream(String id);
}
