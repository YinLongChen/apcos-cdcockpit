package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domains.MonitorModel;
import com.jinxin.platform.cdcockpit.pojo.vo.monitor.MonitorForm;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.ResponseResult;
import com.jinxin.platform.cdcockpit.service.MonitorService;
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
@CrossOrigin
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @PostMapping
    public DataResult<MonitorModel> add(@RequestBody MonitorForm form) {
        return new DataResult(HttpStatus.OK.value(), "成功", monitorService.add(form));
    }


    @PutMapping
    public ResponseResult edit(@RequestBody MonitorForm layout) {
        return monitorService.edit(layout) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable String id) {
        return monitorService.delete(id) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }

    @GetMapping("/{id}")
    public DataResult<MonitorModel> get(@PathVariable String id) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", monitorService.getById(id));
    }

    @GetMapping
    public DataResult<List<MonitorModel>> list() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", monitorService.findMonitorAll());
    }

    @GetMapping("/refresh/{id}")
    public ResponseResult refreshStream(@PathVariable("id") String id) {
        return monitorService.refreshStream(id) ? new ResponseResult(HttpStatus.OK.value(), "成功")
                : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }
}
