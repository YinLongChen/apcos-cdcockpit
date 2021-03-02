package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.domains.CountModel;
import com.jinxin.platform.cdcockpit.pojo.domains.ModelType;

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
