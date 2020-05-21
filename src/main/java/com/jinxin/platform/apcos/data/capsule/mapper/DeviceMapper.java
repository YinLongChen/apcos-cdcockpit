package com.jinxin.platform.apcos.data.capsule.mapper;

import com.jinxin.platform.apcos.data.capsule.pojo.domain.Device;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.device.DeviceCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-20 10:35
 */
@Mapper
public interface DeviceMapper {

    /**
     * 查询设备
     * @param deviceCriteria
     * @return
     */
    List<Device> selectDevice(DeviceCriteria deviceCriteria);

    /**
     * 设备地区统计
     * @return
     */
    List<CountResult> deviceCountByRegion();
}
