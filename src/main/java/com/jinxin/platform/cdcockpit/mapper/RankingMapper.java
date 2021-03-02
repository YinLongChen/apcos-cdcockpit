package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domains.RankingModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 排行榜模型Mapper
 */
@Mapper
public interface RankingMapper {

    /**
     * 根据模型id获取具体的业务数据
     * @param modelId
     * @return
     */
    List<RankingModel> selectRankingByModelId(@Param("modelId") String modelId);
}
