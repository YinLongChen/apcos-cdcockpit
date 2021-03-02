package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.RankingMapper;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.service.RankingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-05-15 14:37
 */
@Service
public class RankingServiceImpl implements RankingService {

    @Resource
    private RankingMapper rankingMapper;

    @Override
    public List<CountResult> rankingByModelId(String modelId) {
        return rankingMapper.selectRankingByModelId(modelId).stream().map(
                u -> CountResult.builder().name(u.getName()).value(u.getTop()).remark(u.getValue().toString()).build()
        ).collect(Collectors.toList());
    }
}
