package com.jinxin.platform.cdcockpit.mapper;

import com.jinxin.platform.cdcockpit.pojo.domain.ModeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-07-10 11:35
 */
@Mapper
public interface ModeMapper {

    @Select("SELECT ID,MODE_NAME AS modeName,ACCOUNT,ENABLE_MODE AS enableMode,MS_ORDER msOrder,MODE_README AS modeReadme,MS_CONTROL as msControl FROM V_ODS_PRO_MODE")
    List<ModeModel> getAll();
}
