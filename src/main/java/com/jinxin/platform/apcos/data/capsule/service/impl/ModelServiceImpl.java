package com.jinxin.platform.apcos.data.capsule.service.impl;

import com.jinxin.platform.apcos.data.capsule.mapper.ModelMapper;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.CountModel;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.ModelType;
import com.jinxin.platform.apcos.data.capsule.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-15 13:47
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
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
