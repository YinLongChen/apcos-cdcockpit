package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Huang LingSong
 * 2020-05-28 10:58
 */
@RestController
@RequestMapping("/capsule/Other")
@CrossOrigin
public class OtherController {

    @Autowired
    private OtherService otherService;

    @GetMapping
    public DataResult selectQuota(@RequestParam String type, @RequestParam Integer feild) {
        return new DataResult(HttpStatus.OK.value(), "成功", otherService.selectQuota(type, feild));
    }
}
