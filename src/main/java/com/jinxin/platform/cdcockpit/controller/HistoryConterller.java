package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.list.ListForm;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/capsule/history")
@CrossOrigin
public class HistoryConterller {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    @Deprecated
    public DataResult findByType(@RequestParam String modelId, @RequestParam Integer feild) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", historyService.selectHistoryData(modelId, feild));
    }

    @PostMapping
    public DataResult findByType(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", historyService.selectHistoryData(form));
    }
}
