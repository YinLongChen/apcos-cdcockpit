package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Huang LingSong
 * 2020-05-15 13:49
 */
@RestController
@RequestMapping("/capsule/model")
@CrossOrigin
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping
    public DataResult findModelByType(@RequestParam String typeId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", modelService.findModelByType(typeId));
    }

    @GetMapping("/type")
    public DataResult findByUserId() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", modelService.findModelType());
    }
}
