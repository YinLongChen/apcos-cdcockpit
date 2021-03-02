package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.LinkMapper;
import com.jinxin.platform.cdcockpit.pojo.domains.LinkModel;
import com.jinxin.platform.cdcockpit.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-03 14:10
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public List<LinkModel> findAll() {
        return linkMapper.selectAll();
    }
}
