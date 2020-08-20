package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domain.ListMap;
import com.jinxin.platform.cdcockpit.pojo.domain.ListOperation;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListMapCriteria;
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
     *
     * @param modelId
     * @return
     */
    @Select("select * from ODS_LIST_MODEL_DATA where MODEL_ID = #{modelId}")
    List<Map<String, Object>> selectByModelId(@Param("modelId") String modelId);

    /**
     * 根据id查询映射关系
     *
     * @param mid
     * @return
     */
    @Select("select ID,MODEL_ID as modelId,COLUMN_ORI as columnOrl,COLUMN_EN as columnEn,COLUMN_CN as columnCn,DATA_TYPE as dataType from ODS_LIST_MODEL_DATA_MAP where ID = #{mid}")
    ListMap getMapById(@Param("mid") String mid);

    /**
     * 根据modelId查询数据于具体业务名称映射关系
     *
     * @param modelId
     * @return
     */
    @Deprecated
    @Select("select ID,MODEL_ID as modelId,COLUMN_ORI as columnOrl,COLUMN_EN as columnEn,COLUMN_CN as columnCn,DATA_TYPE as dataType from ODS_LIST_MODEL_DATA_MAP where MODEL_ID = #{modelId} ORDER BY COLUMN_ORI ASC")
    List<ListMap> selectMap(@Param("modelId") String modelId);

    /**
     * 查询数据于具体业务名称映射关系
     * @param criteria
     * @return
     */
    List<ListMap> selectMaps(ListMapCriteria criteria);

    /**
     * 根据id查询数据于具体业务名称映射关系
     *
     * @param mapId
     * @return
     */
    @Deprecated
    @Select("select ID,MODEL_ID as modelId,COLUMN_ORI as columnOrl,COLUMN_EN as columnEn,COLUMN_CN as columnCn from ODS_LIST_MODEL_DATA_MAP where ID = #{mapId}")
    List<ListMap> selectMapById(@Param("mapId") String mapId);

    /**
     * 根据modelId查询数据操作
     *
     * @param modelId
     * @return
     */
    @Select("SELECT ID,MODEL_ID as modelId,NAME,URL,METHOD,PARAM FROM ODS_LIST_MODEL_DATA_OPERATION WHERE MODEL_ID = #{modelId}")
    List<ListOperation> selectOpreation(@Param("modelId") String modelId);

    boolean saveOpreation(ListOperation operation);

    @Select("select DISTINCT ${column} FROM ODS_LIST_MODEL_DATA")
    List<Object> selectValueInColumn(@Param("column") String column);

    @Deprecated
    @Select("select * from ODS_LIST_MODEL_DATA ${sql} ")
    List<Map<String, Object>> selectData(@Param("sql") String sql);

    @Deprecated
    @Select("select ${groupBy} as name,count(${groupBy}) as value from ODS_LIST_MODEL_DATA ${where} group by ${groupBy}")
    List<Map<String, Object>> selectDataCount(@Param("groupBy") String groupBy, @Param("where") String where);

    @Select("SELECT VIEW_NAME as viewName FROM ODS_LIST_MODEL_VIEW WHERE MODEL_ID = #{modelId}")
    String selectViewByModelId(@Param("modelId") String modelId);

    @Select("select * from ${view} ${sql} ")
    List<Map<String, Object>> selectViewData(@Param("view") String view, @Param("sql") String sql);

    @Select("select ${groupBy} as name,count(${groupBy}) as value from ${view} ${where} group by ${groupBy}")
    List<Map<String, Object>> selectViewDataCount(@Param("view") String view, @Param("groupBy") String groupBy, @Param("where") String where);

    @Select("select ${groupBy} as name,sum(${sum}) as value from ${view} ${where} group by ${groupBy}")
    List<Map<String, Object>> selectViewDataSum(@Param("view") String view, @Param("groupBy") String groupBy,@Param("sum") String sum, @Param("where") String where);

    @Select("select DISTINCT ${column} FROM ${view}")
    List<String> selectValueInViewColumn(@Param("view") String view, @Param("column") String column);
}
