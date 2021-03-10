package com.jinxin.platform.cdcockpit.service;

import com.github.pagehelper.PageInfo;
import com.jinxin.platform.cdcockpit.pojo.domains.CdcockpitTitle;

import java.util.List;

/**
 * (CdcockpitTitle)表服务接口
 *
 * @author cyl
 * @since 2020-12-25 14:05:28
 */
public interface CdcockpitTitleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CdcockpitTitle queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    PageInfo<CdcockpitTitle> queryAllByLimit(int offset, int limit, CdcockpitTitle cdcockpitTitle);

    /**
     * 新增数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 实例对象
     */
    CdcockpitTitle insert(CdcockpitTitle cdcockpitTitle);

    /**
     * 修改数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 实例对象
     */
    CdcockpitTitle update(CdcockpitTitle cdcockpitTitle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 根据对象查找数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 实例对象
     */
    List<CdcockpitTitle> queryAll(CdcockpitTitle cdcockpitTitle);


}