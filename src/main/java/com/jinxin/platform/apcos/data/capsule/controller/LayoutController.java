package com.jinxin.platform.apcos.data.capsule.controller;

import com.jinxin.platform.apcos.data.capsule.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.LayoutForm;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.result.ResponseResult;
import com.jinxin.platform.apcos.data.capsule.service.LayoutService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/capsule/config")
public class LayoutController {

    @Autowired
    private LayoutService layoutService;


    @ApiOperation(value = "添加", notes = "添加")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping
    public ResponseResult add(@RequestBody LayoutForm layout) {
        return layoutService.add(layout) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }


    @ApiOperation(value = "编辑", notes = "编辑")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PutMapping
    public ResponseResult edit(@RequestBody LayoutForm layout) {
        return layoutService.edit(layout) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }


    @ApiOperation(value = "删除", notes = "删除")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable String id) {
        return layoutService.delete(id) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }


    @ApiOperation(value = "根据用户获取布局配置", notes = "根据用户获取布局配置")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    public DataResult findByUserId() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", layoutService.findByUserId());
    }
}
