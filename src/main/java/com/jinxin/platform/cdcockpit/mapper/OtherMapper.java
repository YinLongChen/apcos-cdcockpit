package com.jinxin.platform.cdcockpit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OtherMapper {

    @Select("SELECT * FROM( SELECT ENERGY_QUOTA FROM ODS_ENERGY_USETYPE_DATA WHERE BUSI_MONTH = #{time} and ENERGY_TYPE = #{type}) WHERE rownum = 1")
    Integer selectQuota(@Param("time") String time, @Param("type") String type);
}
