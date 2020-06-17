package com.jinxin.platform.apcos.cockpit.service.impl;

import com.jinxin.platform.apcos.cockpit.mapper.KvMapper;
import com.jinxin.platform.apcos.cockpit.pojo.domain.KvModel;
import com.jinxin.platform.apcos.cockpit.service.KvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-06-17 10:35
 */
@Slf4j
@Service
public class KvServiceImpl implements KvService {

    @Autowired
    private KvMapper kvMapper;

    @Override
    public Map<String, List<KvModel>> findByModelIds(List<String> ids) {
        return kvMapper.selectByModelIds(ids).stream().collect(Collectors.groupingBy(KvModel::getModelId));
    }
}
