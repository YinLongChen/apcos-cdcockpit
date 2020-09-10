package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.exception.CockpitException;
import com.jinxin.platform.cdcockpit.mapper.LayoutMapper;
import com.jinxin.platform.cdcockpit.pojo.domain.Layout;
import com.jinxin.platform.cdcockpit.pojo.enumeration.StatusType;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutCriteria;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutForm;
import com.jinxin.platform.cdcockpit.service.CockpitSupportService;
import com.jinxin.platform.cdcockpit.service.LayoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private CockpitSupportService supportService;


    @Override
    public boolean add(LayoutForm layoutForm) {
        Layout layout = new Layout();
        BeanUtils.copyProperties(layoutForm, layout);
        layout.setUserId(supportService.getWebCurrUser().getUserId());
        if (layout.getStatus() == null) {
            layout.setStatus(StatusType.DRAFT.getValue());
        }
        return layoutMapper.save(layout);
    }

    @Override
    public boolean edit(LayoutForm layoutForm) {
        Layout layout = new Layout();
        BeanUtils.copyProperties(layoutForm, layout);
        layout.setUserId(supportService.getWebCurrUser().getUserId());

        if (1 != layoutMapper.update(layout)) {
            throw new CockpitException("未找到记录");
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (1 != layoutMapper.delete(id)) {
            throw new CockpitException("未找到记录");
        }
        return true;
    }

    @Override
    public List<Layout> findByUserId() {
        return layoutMapper.selectByUserId(supportService.getWebCurrUser().getUserId());
    }

    @Override
    public List<Layout> findRelease(String id) {
        return layoutMapper.selectByStatus(StatusType.RELEASE.getValue(), id);
    }

    @Override
    public List<Layout> find(LayoutCriteria layout) {
        return layoutMapper.select(layout);
    }

    @Override
    public String getProjectName() {
        final String[] name = {null};
        layoutMapper.getProjectName().forEach(s -> {
            if (!StringUtils.isEmpty(s)) {
                name[0] = s;
            }
        });
        return StringUtils.isEmpty(name[0]) ? "请配置项目名称" : name[0];
    }

    @Override
    public boolean updateProjectName(String name) {
        layoutMapper.updateProjectName(name);
        return true;
    }

    @Override
    public boolean updateLogo(String url) {
        layoutMapper.updateLogo(url);
        return true;
    }
}


