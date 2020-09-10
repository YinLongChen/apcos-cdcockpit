package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.pojo.vo.user.LoginUser;
import com.jinxin.platform.cdcockpit.service.CockpitSupportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Huang LingSong
 * 2020-06-05 14:06
 */
@Slf4j
@Service
public class CockpitSupportServiceImpl implements CockpitSupportService {

//    @Autowired
//    private BusinessDelegate businessDelegate;
//
//    private InvokeCfg cfg;
//
//    {
//        this.cfg = new InvokeCfg();
//        this.cfg.setModuleKey("CockpitSupport");
//        this.cfg.setClassName("CockpitService");
//    }

    @Override
    public LoginUser getWebCurrUser() {

        return LoginUser.builder()
                .userId("user_id")
                .userName("userName")
                .orgId("1")
                .build();

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
//        log.info("orgId:{}", jsonObject.getString("orgId"));
//        log.info("userName:{}", jsonObject.getString("userName"));
//
//        return LoginUser.builder()
//                .userId(jsonObject.getString("userId"))
//                .orgId(jsonObject.getString("orgId"))
//                .userName(jsonObject.getString("userName"))
//                .build();
    }
}








