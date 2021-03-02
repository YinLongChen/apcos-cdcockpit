package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.compare.CompareResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.CompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Huang LingSong
 * 2020-05-28 10:58
 */
@RestController
@RequestMapping("/capsule/compare")
@CrossOrigin
public class CompareController {

    @Autowired
    private CompareService compareService;

    @GetMapping
    public DataResult<CompareResult> findModelByType(@RequestParam String modelId, @RequestParam String month) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", compareService.compareByModelId(modelId,month));
    }
}
