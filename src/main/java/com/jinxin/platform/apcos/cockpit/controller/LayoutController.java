package com.jinxin.platform.apcos.cockpit.controller;

import com.jinxin.platform.apcos.cockpit.pojo.domain.Layout;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.LayoutCriteria;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.LayoutForm;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.ResponseResult;
import com.jinxin.platform.apcos.cockpit.service.LayoutService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/capsule/config")
public class LayoutController {

    @Autowired
    private LayoutService layoutService;

    @ApiOperation(value = "添加", notes = "添加[ status 状态（0 草稿，1 发布）]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping
    public ResponseResult add(@RequestBody LayoutForm layout) {
        return layoutService.add(layout) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }


    @ApiOperation(value = "编辑", notes = "编辑[ status 状态（0 草稿，1 发布）]")
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
//
//    @ApiOperation(value = "获取已发布布局配置", notes = "获取已发布布局配置")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功"),
//            @ApiResponse(code = 500, message = "服务器内部错误")
//    })
//    @GetMapping("/release")
//    public DataResult findRelease(@RequestParam(required = false) String id) {
//        return new DataResult<>(HttpStatus.OK.value(), "成功", layoutService.findRelease(id));
//    }


    @ApiOperation(value = "获取布局配置", notes = "获取布局配置")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/find")
    public DataResult<List<Layout>> find(@RequestBody LayoutCriteria layout) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", layoutService.find(layout));
    }

    @ApiOperation(value = "获取项目名称", notes = "获取项目名称")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/project")
    public DataResult<String> getProName() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", layoutService.getProjectName());
    }
}