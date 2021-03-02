package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domains.LinkModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-03 14:10
 */
@Mapper
public interface LinkMapper {

    @Select("SELECT ID,MODEL_ID as modelId,NAME,LINK_URL as linkUrl FROM ODS_LINK_MODEL_DATA")
    List<LinkModel> selectAll();
}
