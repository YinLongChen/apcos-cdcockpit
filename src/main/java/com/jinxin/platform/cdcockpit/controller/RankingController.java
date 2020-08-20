package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.RankingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 排行榜模型
 * @author Huang LingSong
 * 2020-05-15 14:43
 */
@RestController
@RequestMapping("/capsule/rangking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @ApiOperation(value = "根据模型id获取业务数据", notes = "根据模型id获取业务数据")
    @GetMapping
    public DataResult findModelByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", rankingService.rankingByModelId(modelId));
    }
}
