package com.jinxin.platform.apcos.cockpit.controller;

import com.jinxin.platform.apcos.cockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.cockpit.service.ListService;
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
 * 2020-05-18 11:32
 */
@RestController
@RequestMapping("/capsule/list")
public class ListModelController {


    @Autowired
    private ListService listService;


    @ApiOperation(value = "根据类型id获取业务数据 无分页", notes = "根据类型id获取业务数据 无分页")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult findByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findByType(modelId));
    }

    @ApiOperation(value = "根据业务类型获取字段名中英文映射关系", notes = "根据业务类型获取字段名中英文映射关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/map")
    public DataResult findNameMapByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findNameMapByType(modelId));
    }

}
