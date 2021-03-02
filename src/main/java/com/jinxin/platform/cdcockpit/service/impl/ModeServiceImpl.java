package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.ModeMapper;
import com.jinxin.platform.cdcockpit.pojo.domains.ModeModel;
import com.jinxin.platform.cdcockpit.service.ModeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Huang LingSong
 * 2020-07-10 11:42
 */
@Service
public class ModeServiceImpl implements ModeService {

    @Resource
    private ModeMapper modeMapper;

    @Override
    public List<ModeModel> find() {
        return modeMapper.getAll();
    }
}
