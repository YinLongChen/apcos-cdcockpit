package com.jinxin.platform.apcos.data.capsule.service;

import com.jinxin.platform.apcos.data.capsule.pojo.domain.CountModel;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.ModelType;

import java.util.List;

public interface ModelService {

    /**
     * 获取模型分类
     * @return
     */
    List<ModelType> findModelType();

    /**
     * 根据类型id获取具体业务模型列表
     * @param typeId
     * @return
     */
    List<CountModel> findModelByType(String typeId);
}
