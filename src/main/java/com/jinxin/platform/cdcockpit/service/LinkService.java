package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.domains.LinkModel;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-03 14:10
 */
public interface LinkService {
    List<LinkModel> findAll();
}
