package com.jinxin.platform.apcos.cockpit.mapper;

import com.jinxin.platform.apcos.cockpit.pojo.domain.User;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.user.UserCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-04-16 15:25
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<User> selectUser(UserCriteria userCriteria);

    /**
     * 用户性别统计
     * @return
     */
    List<CountResult> userCountByGender();


}
