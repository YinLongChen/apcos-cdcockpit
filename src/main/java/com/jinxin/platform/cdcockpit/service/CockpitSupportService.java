package com.jinxin.platform.cdcockpit.service;

import com.jinxin.platform.cdcockpit.pojo.vo.user.LoginUser;

/**
 * @author Huang LingSong
 * 2020-06-05 14:06
 */
public interface CockpitSupportService {

    /**
     * 获取当前登录用户
     * @return
     */
    LoginUser getWebCurrUser();
}
