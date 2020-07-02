package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domain.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.vo.monitor.MonitorForm;
import com.jinxin.platform.cdcockpit.utils.StringUtil;
import org.apache.ibatis.annotations.Delete;
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

    @Select("SELECT ID,DEVICE_NUM as deviceNum, DEVICE_NAME as deviceName,POSITION,STATUS,RTMP, RTSP FROM ODS_MONITOR_MODEL_DATA WHERE ID = #{id}")
    MonitorModel selectMonitorById(@Param("id") String id);


    @Select("SELECT ID,DEVICE_NUM as deviceNum, DEVICE_NAME as deviceName,POSITION,STATUS,RTMP, RTSP FROM ODS_MONITOR_MODEL_DATA")
    List<MonitorModel> selectMonitorAll();

    boolean update(MonitorModel monitorModel);

    boolean save(MonitorForm form);

    @Delete("DELETE FROM ODS_MONITOR_MODEL_DATA WHERE ID = #{id}")
    boolean delete(@Param("id") String id);

    @Select("SELECT ID,DEVICE_NUM as deviceName,POSITION,STATUS,RTMP,RTSP,DEVICE_NUM FROM ODS_MONITOR_MODEL_DATA WHERE ID = #{id}")
    MonitorModel getOne(@Param("id") String id);
}
