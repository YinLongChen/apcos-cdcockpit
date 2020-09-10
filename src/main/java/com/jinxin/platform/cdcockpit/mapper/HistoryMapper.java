package com.jinxin.platform.cdcockpit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Mapper
public interface HistoryMapper {

    @Select("select * from ${view} where time >= #{startTime} and time < #{endTime}")
    List<Map<String, Object>> selectHistoryData(@Param("view") String view, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Select("select * from ${view} ${where} and time >= #{startTime} and time < #{endTime}")
    List<Map<String, Object>> selectHistory(@Param("view") String view,@Param("where") String where,@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
