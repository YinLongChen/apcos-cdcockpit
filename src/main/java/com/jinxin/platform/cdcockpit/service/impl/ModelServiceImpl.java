package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.ModelMapper;
import com.jinxin.platform.cdcockpit.pojo.domains.CountModel;
import com.jinxin.platform.cdcockpit.pojo.domains.ModelType;
import com.jinxin.platform.cdcockpit.service.ModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-15 13:47
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Resource
    private ModelMapper modelMapper;
    @Override
    public List<ModelType> findModelType() {
        return modelMapper.selectModelType();
    }

    @Override
    public List<CountModel> findModelByType(String typeId) {
        return modelMapper.selectModelByType(typeId);
    }
}
