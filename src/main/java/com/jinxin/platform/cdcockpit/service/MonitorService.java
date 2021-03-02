package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.domains.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.vo.monitor.MonitorForm;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-29 9:59
 */
public interface MonitorService {

    /**
     * 添加摄像头
     * @param form
     * @return
     */
    MonitorModel add(MonitorForm form);

    /**
     * 编辑
     * @param form
     * @return
     */
    boolean edit(MonitorForm form);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 获取所有的业务数据
     *
     * @return
     */
    List<MonitorModel> findMonitorAll();

    MonitorModel getById(String id);

    boolean refreshStream(String id);
}
