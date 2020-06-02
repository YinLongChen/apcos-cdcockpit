package com.jinxin.platform.apcos.data.capsule.service.impl;

import com.jinxin.platform.apcos.data.capsule.mapper.LayoutMapper;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.Layout;
import com.jinxin.platform.apcos.data.capsule.pojo.enumeration.StatusType;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.LayoutCriteria;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.LayoutForm;
import com.jinxin.platform.apcos.data.capsule.service.LayoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-04-16 15:21
 */
@Slf4j
@Service
public class LayoutServiceImpl implements LayoutService {

    @Autowired
    private LayoutMapper layoutMapper;

    private String userId = "user_id";

    @Override
    public boolean add(LayoutForm layoutForm) {
        Layout layout = new Layout();
        BeanUtils.copyProperties(layoutForm, layout);
        layout.setUserId(userId);
        return layoutMapper.save(layout);
    }

    @Override
    public boolean edit(LayoutForm layoutForm) {
        Layout layout = new Layout();
        BeanUtils.copyProperties(layoutForm, layout);
        layout.setUserId(userId);

        if (1 != layoutMapper.update(layout)) {
            throw new RuntimeException("未找到记录");
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (1 != layoutMapper.delete(id)) {
            throw new RuntimeException("未找到记录");
        }
        return true;
    }

    @Override
    public Layout findByUserId() {
        return layoutMapper.selectByUserId(userId);
    }

    @Override
    public List<Layout> findRelease(String id) {
        return layoutMapper.selectByStatus(StatusType.RELEASE.getValue(), id);
    }

    @Override
    public List<Layout> find(LayoutCriteria layout) {
        return layoutMapper.select(layout);
    }
}


