package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.KvService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-17 10:38
 */
@RestController
@RequestMapping("/capsule/kv")
public class KvController {

    @Autowired
    private KvService kvService;


    @ApiOperation(value = "根据modelIds获取业务数据", notes = "根据modelIds获取业务数据")
    @PostMapping
    public DataResult findByType(@RequestBody List<String> modelIds) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", kvService.findByModelIds(modelIds));
    }
}
