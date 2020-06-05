package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.domain.Device;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.DeviceForm;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.Paging;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-20 11:07
 */
public interface DeviceService {

    /**
     * 分页查询设备
     * @param deviceForm
     * @return
     */
     Paging<Device> findDevicePage(DeviceForm deviceForm);

    /**
     * 最近一天/一月/一年注册设备统计
     * @param field
     * @return
     */
    List<CountResult> deviceCountByTime(int field);

    /**
     * 设备地区统计
     * @return
     */
    List<CountResult> deviceCountByRegion();

    /**
     * 设备类型统计
     * @return
     */
    List<CountResult> deviceCountByProductCode();
}
