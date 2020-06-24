package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domain.CompareModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Huang LingSong
 * 2020-05-28 10:56
 */
@Mapper
public interface CompareMapper {

    /**
     * 根据模型id和month获取具体的业务数据
     *
     * @param modelId
     * @param month
     * @return
     */
    @Select("SELECT ID,MODEL_ID AS modelId,MONTH,UNIT,VALUE FROM ODS_COMPARE_MODEL_DATA WHERE MODEL_ID =#{modelId} and MONTH = #{month}")
    CompareModel selectCompareDataByModelIdAndMonth(@Param("modelId") String modelId, @Param("month") String month);

    /**
     * 求和
     * @param modelId
     * @return
     */
    @Select("SELECT SUM(VALUE) FROM ODS_COMPARE_MODEL_DATA WHERE MODEL_ID =#{modelId}")
    Integer countTatalByModelId(@Param("modelId") String modelId);
}
