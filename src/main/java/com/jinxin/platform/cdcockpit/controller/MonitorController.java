package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domain.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutForm;
import com.jinxin.platform.cdcockpit.pojo.vo.monitor.MonitorForm;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.ResponseResult;
import com.jinxin.platform.cdcockpit.service.MonitorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "添加", notes = "添加")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping
    public DataResult<MonitorModel> add(@RequestBody MonitorForm form) {
        return new DataResult(HttpStatus.OK.value(), "成功", monitorService.add(form));
    }


    @ApiOperation(value = "编辑", notes = "编辑")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PutMapping
    public ResponseResult edit(@RequestBody MonitorForm layout) {
        return monitorService.edit(layout) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable String id) {
        return monitorService.delete(id) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }

    @ApiOperation(value = "获取摄像头", notes = "获取摄像头")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/{id}")
    public DataResult<MonitorModel> get(@PathVariable String id) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", monitorService.getById(id));
    }

    @ApiOperation(value = "摄像头列表", notes = "摄像头列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult<List<MonitorModel>> list() {
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
