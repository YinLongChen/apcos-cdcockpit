package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.ModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang LingSong
 * 2020-07-10 11:43
 */
@RestController
@RequestMapping("/capsule/mode")
@CrossOrigin
public class ModeController {

    @Autowired
    private ModeService modeService;

    @GetMapping
    public DataResult findByType() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", modeService.find());
    }
}
