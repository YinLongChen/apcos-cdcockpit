package com.jinxin.platform.apcos.cockpit.controller;

import com.jinxin.platform.apcos.cockpit.pojo.domain.MonitorModel;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.cockpit.service.MonitorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-29 10:00
 */
@RestController
@RequestMapping("/capsule/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;


    @ApiOperation(value = "根据模型id获取业务数据", notes = "根据模型id获取业务数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult<List<MonitorModel>> findModeAlllByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", monitorService.monitorByModelId(modelId));
    }
}
