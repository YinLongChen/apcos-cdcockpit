package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.domain.ListOperation;

import java.util.List;
import java.util.Map;

/**
 * 列表模型
 *
 * @author Huang LingSong
 * 2020-05-18 11:27
 */
public interface ListService {

    /**
     * 根据业务类型获取数据 无分页
     *
     * @param modelId
     * @return
     */
    List<Map<String, Object>> findByType(String modelId);

    /**
     * 根据业务类型获取字段名中英文映射关系
     *
     * @param modelId
     * @return
     */
    Map<String, String> findNameMapByType(String modelId);

    /**
     * 根据业务类型获取数据表格操作
     * @param modelId
     * @return
     */
    List<ListOperation> findOperationByModelId(String modelId);

//    /**
//     * 根据modelId和 上报时间范围 查询设备上报数据（设备上报数据列表专用）
//     * @param modelId
//     * @param field 上报时间范围 5-一天之内，3-一周之内，2-一月之内，1-一年之内 ,上报时间需要放置在 COLUMN_15中
//     * @return
//     */
//    List<Map<String, Object>> findReportByType(String modelId,Integer field);
}
