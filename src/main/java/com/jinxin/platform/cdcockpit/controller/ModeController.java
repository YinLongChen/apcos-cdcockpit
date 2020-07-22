package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.ModeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang LingSong
 * 2020-07-10 11:43
 */
@RestController
@RequestMapping("/capsule/mode")
public class ModeController {

    @Autowired
    private ModeService modeService;

    @ApiOperation(value = "获取模式列表", notes = "获取模式列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult findByType() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", modeService.find());
    }
}