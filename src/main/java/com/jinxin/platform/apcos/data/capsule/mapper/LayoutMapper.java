package com.jinxin.platform.apcos.data.capsule.mapper;

import com.jinxin.platform.apcos.data.capsule.pojo.domain.Layout;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Huang LingSong
 * 2020-04-16 15:25
 */
@Mapper
public interface LayoutMapper {

    /**
     * 添加布局配置
     * @param layout
     * @return
     */
    boolean save(Layout layout);

    /**
     * 编辑布局配置
     * @param layout
     * @return
     */
    int update(Layout layout);

    /**
     * 删除布局配置
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 获取用户布局配置
     * @param userId
     * @return
     */
    Layout seleceByUserId(@Param("userId") String userId);
}
