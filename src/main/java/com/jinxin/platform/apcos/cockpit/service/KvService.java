package com.jinxin.platform.apcos.cockpit.service;

import com.jinxin.platform.apcos.cockpit.pojo.domain.KvModel;

import java.util.List;
import java.util.Map;

/**
 * @author Huang LingSong
 * 2020-06-17 10:35
 */
public interface KvService {

    Map<String, List<KvModel>> findByModelIds(List<String> ids);
}
