package com.jinxin.platform.apcos.cockpit.mapper;

import com.jinxin.platform.apcos.cockpit.pojo.domain.ListMap;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ListOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 列表模型
 */
@Mapper
public interface ListMapper {
    /**
     * 根据modelId查询具体业务数据
     * @param modelId
     * @return
     */
    @Select("select * from ODS_LIST_MODEL_DATA where MODEL_ID = #{modelId}")
    List<Map<String,Object>> selectByModelId(@Param("modelId") String modelId);

    /**
     * 根据modelId查询数据于具体业务名称映射关系
     * @param modelId
     * @return
     */
    @Select("select ID,MODEL_ID as modelId,COLUMN_ORI as columnOrl,COLUMN_EN as columnEn,COLUMN_CN as columnCn from ODS_LIST_MODEL_DATA_MAP where MODEL_ID = #{modelId} ORDER BY COLUMN_ORI ASC")
    List<ListMap> selectMap(@Param("modelId") String modelId);

    /**
     * 根据modelId查询数据操作
     * @param modelId
     * @return
     */
    @Select("SELECT ID,MODEL_ID as modelId,NAME,URL,METHOD,PARAM FROM ODS_LIST_MODEL_DATA_OPERATION WHERE MODEL_ID = #{modelId}")
    List<ListOperation> selectOpreation(@Param("modelId") String modelId);
}
