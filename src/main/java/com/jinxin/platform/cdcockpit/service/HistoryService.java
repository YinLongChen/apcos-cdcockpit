package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.vo.list.Line;

public interface HistoryService {

    Line selectHistoryData(String modelId, Integer field);
}
