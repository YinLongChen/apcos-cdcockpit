package com.jinxin.platform.apcos.data.capsule.mapper;

import com.jinxin.platform.apcos.data.capsule.pojo.domain.Layout;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.LayoutCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-04-16 15:25
 */
@Mapper
public interface LayoutMapper {

    /**
     * 添加布局配置
     *
     * @param layout
     * @return
     */
    boolean save(Layout layout);

    /**
     * 编辑布局配置
     *
     * @param layout
     * @return
     */
    int update(Layout layout);

    /**
     * 删除布局配置
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 获取用户布局配置
     *
     * @param userId
     * @return
     */
    Layout selectByUserId(@Param("userId") String userId);

    /**
     * 获取布局
     *
     * @param status
     * @return
     */
    List<Layout> selectByStatus(@Param("status") Integer status, @Param("id") String id);

    /**
     * 获取布局
     * @param layoutCriteria
     * @return
     */
    List<Layout> select(LayoutCriteria layoutCriteria);
}
