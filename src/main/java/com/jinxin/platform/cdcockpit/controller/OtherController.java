package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.OtherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang LingSong
 * 2020-05-28 10:58
 */
@RestController
@RequestMapping("/capsule/Other")
public class OtherController {

    @Autowired
    private OtherService otherService;

    @ApiOperation(value = "获取能源预警值", notes = "获取能源预警值")
    @GetMapping
    public DataResult selectQuota(@RequestParam String type, @RequestParam Integer feild) {
        return new DataResult(HttpStatus.OK.value(), "成功", otherService.selectQuota(type, feild));
    }
}
