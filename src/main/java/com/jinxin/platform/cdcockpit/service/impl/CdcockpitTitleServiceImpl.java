package com.jinxin.platform.cdcockpit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinxin.platform.cdcockpit.mapper.CdcockpitTitleMapper;
import com.jinxin.platform.cdcockpit.pojo.domains.CdcockpitTitle;
import com.jinxin.platform.cdcockpit.service.CdcockpitTitleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CdcockpitTitle)表服务实现类
 *
 * @author cyl
 * @since 2020-12-25 14:05:28
 */
@Service("cdcockpitTitleService")
public class CdcockpitTitleServiceImpl implements CdcockpitTitleService {
    @Resource
    private CdcockpitTitleMapper cdcockpitTitleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CdcockpitTitle queryById(String id) {
        return this.cdcockpitTitleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo<CdcockpitTitle> queryAllByLimit(int offset, int limit, CdcockpitTitle cdcockpitTitle) {
        PageHelper.startPage(offset, limit);
        PageInfo<CdcockpitTitle> pageInfo = new PageInfo<>(cdcockpitTitleMapper.queryAll(cdcockpitTitle));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 实例对象
     */
    @Override
    public CdcockpitTitle insert(CdcockpitTitle cdcockpitTitle) {
        this.cdcockpitTitleMapper.insert(cdcockpitTitle);
        return cdcockpitTitle;
    }

    /**
     * 修改数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 实例对象
     */
    @Override
    public CdcockpitTitle update(CdcockpitTitle cdcockpitTitle) {
        this.cdcockpitTitleMapper.update(cdcockpitTitle);
        return this.queryById(cdcockpitTitle.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.cdcockpitTitleMapper.deleteById(id) > 0;
    }


    /**
     * 根据对象查找数据
     *
     * @param cdcockpitTitle 实例对象
     * @return 实例对象
     */
    @Override
    public List<CdcockpitTitle> queryAll(CdcockpitTitle cdcockpitTitle) {

        return this.cdcockpitTitleMapper.queryAll(cdcockpitTitle);
    }
}