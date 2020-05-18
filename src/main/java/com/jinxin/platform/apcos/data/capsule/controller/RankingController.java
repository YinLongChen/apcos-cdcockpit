package com.jinxin.platform.apcos.data.capsule.controller;

import com.jinxin.platform.apcos.data.capsule.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.data.capsule.service.RankingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang LingSong
 * 2020-05-15 14:43
 */
@RestController
@RequestMapping("/capsule/rangking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @ApiOperation(value = "根据模型id获取业务数据", notes = "根据模型id获取业务数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult findModelByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", rankingService.RankingByModelId(modelId));
    }
}
