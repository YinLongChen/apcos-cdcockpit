package com.jinxin.platform.apcos.cockpit.mapper;

import com.jinxin.platform.apcos.cockpit.pojo.domain.CountModel;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ModelType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-15 11:38
 */
@Mapper
public interface ModelMapper {

    /**
     * 获取模型分类
     * @return
     */
    @Select("SELECT ID AS id,TYPE_NAME AS typeName FROM ODS_MODEL_TYPE_CONFIG")
    List<ModelType> selectModelType();

    /**
     * 根据类型id获取具体业务模型列表
     * @param typeId
     * @return
     */
    @Select("SELECT m.ID,m.MODEL_NAME AS modelName,m.TYPE_ID AS typeId,c.TYPE_NAME AS typeName FROM ODS_MODEL_CONFIG m LEFT JOIN ODS_MODEL_TYPE_CONFIG c ON m.TYPE_ID = c.ID  WHERE c.ID = #{typeId}")
    List<CountModel> selectModelByType(@Param("typeId") String typeId);
}
