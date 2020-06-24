package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.domain.User;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.Paging;
import com.jinxin.platform.cdcockpit.pojo.vo.user.UserForm;

import java.util.List;

public interface CountUserService {

    /**
     * 分页查询用户
     *
     * @param userForm
     * @return
     */
    Paging<User> findUsersPage(UserForm userForm);

    /**
     * 用户性别统计
     *
     * @return
     */
    List<CountResult> userCountByGender();

    /**
     * 用户年龄段统计
     *
     * @return
     */
    List<CountResult> userCountByAgeGroup();


    /**
     * 最近一天/一月/一年注册用户统计
     *
     * @return
     */
    List<CountResult> userCountByTime(int field);

    /**
     * 用户地区统计
     * @return
     */
    List<CountResult> userCountByRegion();
}
