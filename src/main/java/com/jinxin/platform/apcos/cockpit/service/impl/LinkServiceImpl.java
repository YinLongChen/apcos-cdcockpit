package com.jinxin.platform.apcos.cockpit.service.impl;

import com.jinxin.platform.apcos.cockpit.mapper.LinkMapper;
import com.jinxin.platform.apcos.cockpit.pojo.domain.LinkModel;
import com.jinxin.platform.apcos.cockpit.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-03 14:10
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<LinkModel> findAll() {
        return linkMapper.selectAll();
    }
}
