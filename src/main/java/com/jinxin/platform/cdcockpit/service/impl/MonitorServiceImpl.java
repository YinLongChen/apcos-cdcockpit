package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.MonitorMapper;
import com.jinxin.platform.cdcockpit.pojo.domains.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.enumeration.MonitorStatus;
import com.jinxin.platform.cdcockpit.pojo.vo.monitor.MonitorForm;
import com.jinxin.platform.cdcockpit.service.MonitorService;
import com.jinxin.platform.cdcockpit.utils.RTMPGrabberRecorderTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-05-29 10:00
 */
@Slf4j
@Service
public class MonitorServiceImpl implements MonitorService {

    @Resource
    private MonitorMapper monitorMapper;

    @Autowired
    private ExecutorService taskExecutor;

    private String rtmp = "rtmp://113.204.9.70:1935/live/";
//    private String rtmp = "rtmp://192.168.3.76:1935/live/";

    @Override
    public MonitorModel add(MonitorForm form) {
        if (!monitorMapper.save(form)) {
            return null;
        }
        MonitorModel monitorModel = new MonitorModel();
        BeanUtils.copyProperties(form, monitorModel);
        refresh(monitorModel);
        return monitorModel;
    }

    @Override
    public boolean edit(MonitorForm form) {
        MonitorModel monitorModel = new MonitorModel();
        BeanUtils.copyProperties(form, monitorModel);
        return monitorMapper.update(monitorModel);
    }

    @Override
    public boolean delete(String id) {
        return monitorMapper.delete(id);
    }

    @Override
    public List<MonitorModel> findMonitorAll() {
        return monitorMapper.selectMonitorAll().stream()
                .map(m -> {
                    if (StringUtils.isEmpty(m.getRtmp())) {
                        refresh(m);
                    }
                    return m;
                }).collect(Collectors.toList());
    }

    @Override
    public MonitorModel getById(String id) {
        return monitorMapper.getOne(id);
    }


    @Override
    public boolean refreshStream(String id) {
        MonitorModel m = monitorMapper.selectMonitorById(id);
        return refresh(m);
    }

    /**
     * 刷新视频流
     *
     * @param monitorModel
     */
    private boolean refresh(MonitorModel monitorModel) {

        if (StringUtils.isEmpty(monitorModel.getRtsp())) {
            return false;
        }

        if (StringUtils.isEmpty(monitorModel.getRtmp())) {
            monitorModel.setRtmp(this.rtmp + UUID.randomUUID().toString().replaceAll("-", ""));
        }


        Future<Boolean> future = taskExecutor.submit(new RTMPGrabberRecorderTask(monitorModel.getRtmp(), monitorModel.getRtsp()));


        try {
            future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.info("摄像头[{}]流刷新成功", monitorModel.getDeviceName());
            monitorModel.setStatus(MonitorStatus.ONLINE.getValue());
            monitorMapper.update(monitorModel);
            return true;
        } catch (Exception e) {
            log.info("摄像头[{}]流刷新失败", monitorModel.getDeviceName());
            e.printStackTrace();
        }
        monitorModel.setStatus(MonitorStatus.OFFLINE.getValue());
        monitorMapper.update(monitorModel);
        return false;
    }
}
