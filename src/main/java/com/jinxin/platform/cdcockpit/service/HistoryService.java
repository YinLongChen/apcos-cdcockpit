package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.vo.list.Line;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListForm;

public interface HistoryService {
    @Deprecated
    Line selectHistoryData(String modelId, Integer field);

    Line selectHistoryData(ListForm form);
}
