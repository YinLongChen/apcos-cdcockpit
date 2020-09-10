package com.jinxin.platform.cdcockpit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinxin.platform.base.common.pojo.InvokeCfg;
import com.jinxin.platform.base.common.pojo.JsonResult;
import com.jinxin.platform.base.common.support.BusinessDelegate;
import com.jinxin.platform.cdcockpit.pojo.vo.user.LoginUser;
import com.jinxin.platform.cdcockpit.service.CockpitSupportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Huang LingSong
 * 2020-06-05 14:06
 */
@Slf4j
@Service
public class CockpitSupportServiceImpl implements CockpitSupportService {

    @Autowired
    private BusinessDelegate businessDelegate;

    private InvokeCfg cfg;

    {
        this.cfg = new InvokeCfg();
        this.cfg.setModuleKey("CockpitSupport");
        this.cfg.setClassName("CockpitService");
    }

    @Override
    public LoginUser getWebCurrUser() {

        return LoginUser.builder()
                .userId("user_id")
                .userName("userName")
                .orgId("1")
                .build();
//
//        this.cfg.setFunName("getWebCurrUser");
//        this.cfg.setArgs(new HashMap<String, Object>() {{
//        }});
//
//
//        JsonResult jsonResult = businessDelegate.invoke(this.cfg);
//        if (jsonResult.getStatus() != HttpStatus.OK.value()) {
//            log.info("调用模块接口错误：{}", jsonResult.getMsg());
//            return null;
//        }
//        JSONObject jsonObject = (JSONObject) JSON.parse(jsonResult.getData().toString());
//        log.info("----------------------------");
//        log.info("JSON:{}", jsonObject);
//        log.info("userId:{}", jsonObject.getString("userId"));
//        log.info("userName:{}", jsonObject.getString("userName"));
//
//        return LoginUser.builder()
//                .userId(jsonObject.getString("userId"))
//                .orgId(getOrgId(jsonObject.getString("userId")))
//                .userName(jsonObject.getString("userName"))
//                .build();
    }

    public String getOrgId(String userId) {
        return "123456";
//        this.cfg.setFunName("getOrgId");
//        this.cfg.setArgs(new HashMap<String, Object>() {{
//            put("userId", userId);
//        }});
//        JsonResult jsonResult = businessDelegate.invoke(this.cfg);
//        if (jsonResult.getStatus() != HttpStatus.OK.value()) {
//            log.info("调用模块接口错误：{}", jsonResult.getMsg());
//            return null;
//        }
//        JSONObject jsonObject = (JSONObject) JSON.parse(jsonResult.getData().toString());
//        log.info("----------------------------");
//        log.info("JSON:{}", jsonObject);
//        log.info("orgId:{}", jsonObject.getString("orgId"));
//        return jsonObject.getString("orgId");
    }
}








