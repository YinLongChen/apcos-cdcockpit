package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.compare.CompareResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.CompareService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang LingSong
 * 2020-05-28 10:58
 */
@RestController
@RequestMapping("/capsule/compare")
public class CompareController {

    @Autowired
    private CompareService compareService;

    @ApiOperation(value = "根据模型id获取业务数据", notes = "month 月份（格式如：2019-12）")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult<CompareResult> findModelByType(@RequestParam String modelId, @RequestParam String month) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", compareService.compareByModelId(modelId,month));
    }
}
