package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.list.ListForm;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.HistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/capsule/history")
public class HistoryConterller {

    @Autowired
    private HistoryService historyService;

    @ApiOperation(value = "根据modelIds获取业务数据", notes = "1-按年统计，2-按月统计")
    @GetMapping
    @Deprecated
    public DataResult findByType(@RequestParam String modelId, @RequestParam Integer feild) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", historyService.selectHistoryData(modelId, feild));
    }

    @ApiOperation(value = "根据modelIds获取业务数据", notes = "1-按年统计，2-按月统计")
    @PostMapping
    public DataResult findByType(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", historyService.selectHistoryData(form));
    }
}
