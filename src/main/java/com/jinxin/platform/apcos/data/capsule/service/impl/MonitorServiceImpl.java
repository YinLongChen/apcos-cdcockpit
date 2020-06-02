package com.jinxin.platform.apcos.data.capsule.service.impl;

import com.jinxin.platform.apcos.data.capsule.mapper.MonitorMapper;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.MonitorModel;
import com.jinxin.platform.apcos.data.capsule.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-29 10:00
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    public List<MonitorModel> monitorByModelId(String modelId) {
        return monitorMapper.selectMonitorByModelId(modelId);
    }
}
