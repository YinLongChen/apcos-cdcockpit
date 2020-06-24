package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domain.MonitorModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-29 9:59
 */
@Mapper
public interface MonitorMapper {

    @Select("SELECT ID,MODEL_ID as modelId,DEVICE_ID as deviceId, DEVICE_NAME as deviceName,POSITION,STATUS,RTMP_URL as rtmpUrl FROM ODS_MONITOR_MODEL_DATA WHERE MODEL_ID = #{modelId}")
    List<MonitorModel> selectMonitorByModelId(@Param("modelId") String modelId);


    @Select("SELECT ID,MODEL_ID as modelId,DEVICE_ID as deviceId, DEVICE_NAME as deviceName,POSITION,STATUS,RTMP_URL as rtmpUrl FROM ODS_MONITOR_MODEL_DATA")
    List<MonitorModel> selectMonitorAll();
}
