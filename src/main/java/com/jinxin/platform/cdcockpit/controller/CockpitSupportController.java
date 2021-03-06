package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.CockpitSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capsule/support")
@CrossOrigin
public class CockpitSupportController {

    @Autowired
    private CockpitSupportService supportService;

    @GetMapping
    public DataResult getWebCurrUser() {
        return new DataResult(HttpStatus.OK.value(), "成功", supportService.getWebCurrUser());
    }
}
