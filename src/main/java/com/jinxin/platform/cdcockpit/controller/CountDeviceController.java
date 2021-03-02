package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domains.Device;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountStrResult;
import com.jinxin.platform.cdcockpit.pojo.vo.device.DeviceForm;
import com.jinxin.platform.cdcockpit.pojo.vo.device.RepairForm;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.PagingResult;
import com.jinxin.platform.cdcockpit.service.DeviceReportService;
import com.jinxin.platform.cdcockpit.service.DeviceService;
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
@CrossOrigin
public class CountDeviceController {


    @Autowired
    private DeviceReportService reportService;

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/findDevice")
    public PagingResult<Device> findDevice(@RequestBody DeviceForm form) {
        return new PagingResult<>(HttpStatus.OK.value(), "成功", deviceService.findDevicePage(form));
    }


    @Deprecated
    @GetMapping("/findReportType")
    public DataResult findReportType() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.findReportType());
    }

    @Deprecated
    @GetMapping("/countReportByTime")
    public DataResult<List<CountResult>> reportCountByTime(@RequestParam Integer field, @RequestParam String type) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", reportService.reportCountByTime(field, type));
    }


    @GetMapping("/countByRegion")
    public DataResult<List<CountResult>> deviceCountByRegion() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.deviceCountByRegion());
    }


    @GetMapping("/countByTime")
    public DataResult<List<CountResult>> countByTime(@RequestParam Integer field) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.deviceCountByTime(field));
    }


    @GetMapping("/countByProduct")
    public DataResult<List<CountResult>> countByProduct() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.deviceCountByProductCode());
    }

    @GetMapping("/sumDev")
    public DataResult sumDev() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.sumDev());
    }

    @PostMapping("/countRepair")
    public DataResult<List<CountStrResult>> countRepair(@RequestBody RepairForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", deviceService.deviceRepair(form));
    }
}


