package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 排行榜模型
 * @author Huang LingSong
 * 2020-05-15 14:43
 */
@RestController
@RequestMapping("/capsule/rangking")
@CrossOrigin
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping
    public DataResult findModelByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", rankingService.rankingByModelId(modelId));
    }
}
