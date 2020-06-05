package com.jinxin.platform.apcos.cockpit.service;

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
     * @param type
     * @return
     */
    List<Map<String, Object>> findByType(String type);

    /**
     * 根据业务类型获取字段名中英文映射关系
     *
     * @param type
     * @return
     */
    Map<String, String> findNameMapByType(String type);
}
