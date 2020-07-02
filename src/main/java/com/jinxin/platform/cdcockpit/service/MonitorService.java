package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.domain.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutForm;
import com.jinxin.platform.cdcockpit.pojo.vo.monitor.MonitorForm;
import com.jinxin.platform.cdcockpit.utils.StringUtil;

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
    boolean add(MonitorForm form);

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


    boolean refreshStream(String id);
}
