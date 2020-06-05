package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.domain.MonitorModel;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-29 9:59
 */
public interface MonitorService {


    /**
     * 根据模型id获取具体的业务数据
     * @param modelId
     * @return
     */
    List<MonitorModel> monitorByModelId(String modelId);
}
