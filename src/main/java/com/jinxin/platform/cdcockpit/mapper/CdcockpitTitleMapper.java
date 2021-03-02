package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domains.CdcockpitTitle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (CdcockpitTitle)表数据库访问层
 *
 * @author cyl
 * @since 2020-12-25 14:05:28
 */
@Mapper
public interface CdcockpitTitleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CdcockpitTitle queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CdcockpitTitle> queryAllByLimit();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param cdcockpitTitle 实例对象
     * @return 对象列表
     */
    List<CdcockpitTitle> queryAll(CdcockpitTitle cdcockpitTitle);

    /**
     * 新增数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 影响行数
     */
    int insert(CdcockpitTitle cdcockpitTitle);

    /**
     * 修改数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 影响行数
     */
    int update(CdcockpitTitle cdcockpitTitle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}