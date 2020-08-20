package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.ModelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang LingSong
 * 2020-05-15 13:49
 */
@RestController
@RequestMapping("/capsule/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @ApiOperation(value = "根据类型id获取模型", notes = "根据类型id获取模型")
    @GetMapping
    public DataResult findModelByType(@RequestParam String typeId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", modelService.findModelByType(typeId));
    }

    @ApiOperation(value = "获取模型类型", notes = "获取模型类型")
    @GetMapping("/type")
    public DataResult findByUserId() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", modelService.findModelType());
    }
}
