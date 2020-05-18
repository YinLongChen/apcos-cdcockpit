package com.jinxin.platform.apcos.data.capsule.service;

import com.jinxin.platform.apcos.data.capsule.pojo.domain.Layout;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.LayoutForm;

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
    Layout findByUserId();
}
