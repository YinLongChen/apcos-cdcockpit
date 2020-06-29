package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.MonitorMapper;
import com.jinxin.platform.cdcockpit.pojo.domain.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.enumeration.MonitorStatus;
import com.jinxin.platform.cdcockpit.service.MonitorService;
import com.jinxin.platform.cdcockpit.utils.RTMPGrabberRecorderTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-05-29 10:00
 */
@Slf4j
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    @Autowired
    private ExecutorService taskExecutor;


    @Override
    public List<MonitorModel> findMonitorAll() {
        return monitorMapper.selectMonitorAll().stream()
                .filter(f -> !StringUtils.isEmpty(f.getRtsp()))
                .map(m -> {
                    if (StringUtils.isEmpty(m.getRtmp())) {
                        m.setRtmp("rtmp://113.204.9.70:1935/live/" + UUID.randomUUID().toString().replaceAll("-", ""));
                        log.info("摄像头[{}-{}]推流", m.getDeviceName(), m.getDeviceNum());
                        refresh(m);
                        monitorMapper.update(m);
                    }
                    return m;
                }).collect(Collectors.toList());
    }

    @Override
    public boolean refreshStream(String id) {
        MonitorModel m = monitorMapper.selectMonitorById(id);
        log.info("摄像头[{}-{}]流刷新", m.getDeviceName(), m.getDeviceNum());
        return refresh(m);
    }

    /**
     * 刷新视频流
     *
     * @param monitorModel
     */
    private boolean refresh(MonitorModel monitorModel) {
        Future<Boolean> future = taskExecutor.submit(new RTMPGrabberRecorderTask(monitorModel.getRtmp(), monitorModel.getRtsp()));
        try {
            future.get(5, TimeUnit.SECONDS);
            monitorModel.setStatus(MonitorStatus.OFFLINE.getValue());
            monitorMapper.update(monitorModel);
            return false;
        } catch (Exception e) {
            log.info("摄像头[{}-{}]流刷新成功", monitorModel.getDeviceName(), monitorModel.getDeviceNum());
        }
        return true;
    }
}
