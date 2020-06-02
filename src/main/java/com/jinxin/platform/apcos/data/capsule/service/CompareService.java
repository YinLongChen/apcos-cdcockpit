package com.jinxin.platform.apcos.data.capsule.service;

import com.jinxin.platform.apcos.data.capsule.pojo.vo.compare.CompareResult;

/**
 * @author Huang LingSong
 * 2020-05-28 10:57
 */
public interface CompareService {

    CompareResult compareByModelId(String modelId, String month);
}
