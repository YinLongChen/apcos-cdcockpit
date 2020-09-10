package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domain.KvModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-17 10:28
 */
@Mapper
public interface KvMapper {

    List<KvModel> selectByModelIds(List<String> modelIds);

    @Select("SELECT \"ID\",\"MODEL_ID\" as modelId,\"NAME\",\"KEY\",\"VALUE\",\"REMARK\" FROM ODS_KV_MODEL_DATA where FILTER = #{filter, jdbcType=VARCHAR} and MODEL_ID = #{modelId, jdbcType=VARCHAR}")
    List<KvModel> selectByModelId(String modelId,String filter);
}
