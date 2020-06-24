package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domain.Device;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.pojo.vo.device.DeviceCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Huang LingSong
 * 2020-05-20 10:35
 */
@Mapper
public interface DeviceMapper {

    /**
     * 查询设备
     *
     * @param deviceCriteria
     * @return
     */
    List<Device> selectDevice(DeviceCriteria deviceCriteria);

    @Select("SELECT COUNT(*) FROM V_ODS_DEVICE_CENTER_INFO")
    Integer sumDev();

    @Select("select DISTINCT ${column} FROM V_ODS_DEVICE_REPAIR")
    List<String> selectValueInColumn(@Param("column") String column);

    @Select("select ${groupBy} as name,count(${groupBy}) as value from V_ODS_DEVICE_REPAIR ${where} group by ${groupBy}")
    List<CountResult> selectViewDataCount(@Param("groupBy") String groupBy, @Param("where") String where);

    @Select("select * from V_ODS_DEVICE_REPAIR ${where}")
    List<Map<String,Object>> selectViewData(@Param("where") String where);

}
