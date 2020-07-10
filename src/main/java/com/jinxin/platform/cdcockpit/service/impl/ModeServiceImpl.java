package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.ModeMapper;
import com.jinxin.platform.cdcockpit.pojo.domain.ModeModel;
import com.jinxin.platform.cdcockpit.service.ModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-07-10 11:42
 */
@Service
public class ModeServiceImpl implements ModeService {

    @Autowired
    private ModeMapper modeMapper;

    @Override
    public List<ModeModel> find() {
        return modeMapper.getAll();
    }
}
