package com.jinxin.platform.apcos.cockpit.mapper;

import com.jinxin.platform.apcos.cockpit.pojo.domain.KvModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-17 10:28
 */
@Mapper
public interface KvMapper {

    List<KvModel> selectByModelIds(List<String> modelIds);
}
