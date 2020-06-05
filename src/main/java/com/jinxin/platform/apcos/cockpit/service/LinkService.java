package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.domain.LinkModel;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-03 14:10
 */
public interface LinkService {
    List<LinkModel> findAll();
}
