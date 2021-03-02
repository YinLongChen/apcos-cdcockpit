package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.pojo.vo.device.ReportCriteria;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.DeviceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-11 14:42
 */
@RestController
@RequestMapping("/capsule/report")
@CrossOrigin
public class ReportController {
    @Autowired
    private DeviceReportService reportService;

    @GetMapping("/findReportType")
    public DataResult findReportType() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.findReportType());
    }

    @GetMapping("/countReportByTime")
    public DataResult<List<CountResult>> reportCountByTime(@RequestParam Integer field, @RequestParam(required = false) String type) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.reportCountByTime(field, type));
    }

    @GetMapping("/countReportByType")
    public DataResult<List<CountResult>> reportCountByType(@RequestParam Integer field) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.reportCountByType(field));
    }


    //    @ApiOperation(value = "获取数据表格操作", notes = "获取数据表格操作")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功"),
//            @ApiResponse(code = 500, message = "服务器内部错误")
//    })
//    @GetMapping("/operation")
//    public DataResult findOperation() {
//        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.findOperation());
//    }
    @PostMapping("/list")
    public DataResult findReport(@RequestBody ReportCriteria criteria) {
        return new DataResult(HttpStatus.OK.value(), "成功", reportService.findReport(criteria));
    }

    @PostMapping("/max")
    public DataResult findMaxTimeReport(@RequestBody ReportCriteria criteria) {
        return new DataResult(HttpStatus.OK.value(), "成功", reportService.findMaxTimeReport(criteria));
    }
}
