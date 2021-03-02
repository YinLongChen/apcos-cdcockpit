package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.KvMapper;
import com.jinxin.platform.cdcockpit.pojo.domains.KvModel;
import com.jinxin.platform.cdcockpit.service.KvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private KvMapper kvMapper;

    @Override
    public Map<String, List<KvModel>> findByModelIds(List<String> ids) {
        if (ids.isEmpty()) {
            return null;
        }
        return kvMapper.selectByModelIds(ids).stream().collect(Collectors.groupingBy(KvModel::getModelId));
    }

    @Override
    public Map<String, List<KvModel>> findByModelIds(List<String> ids, String filter) {
        if (ids.isEmpty()) {
            return null;
        }
        return kvMapper.selectByModelId(ids.get(0), filter).stream().collect(Collectors.groupingBy(KvModel::getModelId));
    }
}
