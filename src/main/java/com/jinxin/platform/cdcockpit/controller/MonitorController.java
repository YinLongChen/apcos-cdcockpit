package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domain.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.ResponseResult;
import com.jinxin.platform.cdcockpit.service.MonitorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @ApiOperation(value = "摄像头列表", notes = "摄像头列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult<List<MonitorModel>> findModeAlll() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", monitorService.findMonitorAll());
    }

    @ApiOperation(value = "刷新摄像头视频流", notes = "刷新摄像头视频流")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/refresh/{id}")
    public ResponseResult refreshStream(@PathVariable("id") String id) {
        return monitorService.refreshStream(id) ? new ResponseResult(HttpStatus.OK.value(), "成功")
                : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }
}
