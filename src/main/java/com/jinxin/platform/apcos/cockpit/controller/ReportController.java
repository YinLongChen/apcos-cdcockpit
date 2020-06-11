package com.jinxin.platform.apcos.cockpit.controller;

import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.cockpit.service.DeviceReportService;
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
 * 2020-06-11 14:42
 */
@RestController
@RequestMapping("/capsule/report")
public class ReportController {
    @Autowired
    private DeviceReportService reportService;

    @ApiOperation(value = "获取上报类型", notes = "获取上报类型")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/findReportType")
    public DataResult findReportType() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.findReportType());
    }

    @ApiOperation(value = "设备上报统计", notes = "设备上报统计(5-天统计，3-周统计，2-月统计，1-年统计)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countReportByTime")
    public DataResult<List<CountResult>> reportCountByTime(@RequestParam Integer field, @RequestParam(required = false) String type) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.reportCountByTime(field, type));
    }

    @ApiOperation(value = "设备上报类型统计", notes = "设备上报统计(5-天统计，3-周统计，2-月统计，1-年统计)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countReportByType")
    public DataResult<List<CountResult>> reportCountByType(@RequestParam Integer field) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.reportCountByType(field));
    }
}
