package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.domains.Layout;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutCriteria;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutForm;

import java.util.List;

public interface LayoutService {

    /**
     * 添加布局配置
     * @param layoutForm
     * @return
     */
    boolean add(LayoutForm layoutForm);

    /**
     * 编辑布局配置
     * @param layout
     * @return
     */
    boolean edit(LayoutForm layout);

    /**
     * 删除布局配置
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 获取用户布局配置
     * @return
     */
    List<Layout> findByUserId();

    /**
     * 获取已发布的布局
     * @param number
     * @return
     */
    List<Layout> findRelease(String number);


    /**
     * 获取布局
     * @param layout
     * @return
     */
    List<Layout> find(LayoutCriteria layout);

    /**
     * 获取项目名称
     * @return
     */
    String getProjectName();

    /**
     * 更新项目名
     * @param name
     * @return
     */
    boolean updateProjectName(String name);

    boolean updateLogo(String url);
}
