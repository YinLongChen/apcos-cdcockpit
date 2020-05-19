package com.jinxin.platform.apcos.data.capsule.service;

import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.CountResult;

import java.util.List;

/**
 * 排行榜模型
 */
public interface RankingService {

    /**
     * 根据模型id获取具体的业务数据
     * @param modelId
     * @return
     */
    List<CountResult> RankingByModelId(String modelId);
}
