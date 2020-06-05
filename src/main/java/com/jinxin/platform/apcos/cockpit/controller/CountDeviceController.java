package com.jinxin.platform.apcos.cockpit.controller;

import com.jinxin.platform.apcos.cockpit.pojo.domain.Device;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.DeviceForm;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.PagingResult;
import com.jinxin.platform.apcos.cockpit.service.DeviceReportService;
import com.jinxin.platform.apcos.cockpit.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-19 16:42
 */
@RestController
@RequestMapping("/capsule/device")
public class CountDeviceController {


    @Autowired
    private DeviceReportService reportService;

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "设备查询", notes = "设备查询")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/findDevice")
    public PagingResult<Device> findUsers(@RequestBody DeviceForm form) {
        return new PagingResult<>(HttpStatus.OK.value(), "成功", deviceService.findDevicePage(form));
    }

    @ApiOperation(value = "获取上报类型", notes = "获取上报类型")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/findReportType")
    public DataResult findReportType() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.findReportType());
    }

    @ApiOperation(value = "最近一天/一周/一月/一年设备上报统计", notes = "设备上报统计(5-天统计，3-周统计，2-月统计，1-年统计)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countReportByTime")
    public DataResult<List<CountResult>> reportCountByTime(@RequestParam Integer field, @RequestParam String type) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.reportCountByTime(field, type));
    }



    @ApiOperation(value = "设备地区段统计", notes = "设备地区段统计")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByRegion")
    public DataResult<List<CountResult>> deviceCountByRegion() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.deviceCountByRegion());
    }


    @ApiOperation(value = "最近一天/一周/一月/一年注册设备统计", notes = "注册设备统计(5-天统计，3-周统计，2-月统计，1-年统计)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByTime")
    public DataResult<List<CountResult>> userCountByTime(@RequestParam Integer field) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.deviceCountByTime(field));
    }


    @ApiOperation(value = "设备类型统计", notes = "设备类型统计")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByProduct")
    public DataResult<List<CountResult>> userCountByProduct() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.deviceCountByProductCode());
    }
}
