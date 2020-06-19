package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.domain.ListMap;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ListOperation;
import com.jinxin.platform.apcos.cockpit.pojo.vo.list.CubeForm;
import com.jinxin.platform.apcos.cockpit.pojo.vo.list.CubeResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.list.ListForm;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
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
    @Deprecated
    List<Map<String, Object>> findByType(String modelId);

    /**
     * 根据业务类型获取字段名中英文映射关系
     *
     * @param modelId
     * @return
     */
    @Deprecated
    Map<String, String> findNameMapByType(String modelId);

    /**
     * 根据业务类型获取数据表格操作
     * @param modelId
     * @return
     */
    List<ListOperation> findOperationByModelId(String modelId);

    /**
     * 获取约束条件(where/group by)
     * @param modelId
     * @return
     */
    List<ListMap> findColumnByModelId(String modelId);

    /**
     * 获取约束条件(where)参数
     * @param mapId
     * @return
     */
    @Deprecated
    List<Object> findValueInColumn(String mapId);

    /**
     * 获取数据
     * @param form
     * @return
     */
    @Deprecated
    List<Map<String, Object>> findData(ListForm form);

    /**
     * 获取数据
     * @param form
     * @return
     */
    List<Map<String, Object>> findViewData(ListForm form);

    /**
     * 获取多维数据
     * @param form
     * @return
     */
    CubeResult findViewCubeData(CubeForm form);

    /**
     * 根据业务类型获取字段名中英文映射关系
     *
     * @param modelId
     * @return
     */
    Map<String, String> findNameMapByModelId(String modelId);

    /**
     * 获取视图约束条件(where)参数
     * @param mapId
     * @return
     */
    List<String> findValueInViewColumn(String mapId);

}
