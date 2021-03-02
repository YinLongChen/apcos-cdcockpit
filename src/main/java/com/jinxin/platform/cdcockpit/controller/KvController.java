package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.KvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-17 10:38
 */
@RestController
@RequestMapping("/capsule/kv")
@CrossOrigin
public class KvController {

    @Autowired
    private KvService kvService;


    @PostMapping
    public DataResult findByType(@RequestBody List<String> modelIds) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", kvService.findByModelIds(modelIds));
    }

    @PostMapping("/filter")
    public DataResult findByTypes(@RequestBody List<String> modelIds, @RequestParam String filter) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", kvService.findByModelIds(modelIds, filter));
    }
}
