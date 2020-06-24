package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.list.CubeForm;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListForm;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.ListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Huang LingSong
 * 2020-05-18 11:32
 */
@RestController
@RequestMapping("/capsule/list")
public class ListController {


    @Autowired
    private ListService listService;


    @ApiOperation(value = "根据类型id获取业务数据 无分页", notes = "根据类型id获取业务数据 无分页")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping
    @Deprecated
    public DataResult findByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findByType(modelId));
    }

    @ApiOperation(value = "根据业务类型获取字段名中英文映射关系", notes = "根据业务类型获取字段名中英文映射关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/map")
    @Deprecated
    public DataResult findNameMapByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findNameMapByType(modelId));
    }

    @ApiOperation(value = "根据业务类型获取数据表格操作", notes = "根据业务类型获取数据表格操作")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/operation")
    public DataResult findOperationByModelId(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findOperationByModelId(modelId));
    }


    @ApiOperation(value = "获取约束条件(where/group by)", notes = "获取约束条件(where/group by)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/findColumn")
    public DataResult findColumn(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findColumnByModelId(modelId));
    }

    @ApiOperation(value = "获取约束条件(where)参数", notes = "获取约束条件(where)参数")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @Deprecated
    @GetMapping("/findValue")
    public DataResult findValue(@RequestParam String mapId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findValueInColumn(mapId));
    }

    @ApiOperation(value = "获取数据", notes = "获取数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/data")
    @Deprecated
    public DataResult findData(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findData(form));
    }

    @ApiOperation(value = "根据业务类型获取字段名中英文映射关系", notes = "根据业务类型获取字段名中英文映射关系")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/view_map")
    public DataResult findNameMapByModelId(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findNameMapByModelId(modelId));
    }

    @ApiOperation(value = "获取约束条件(where)参数", notes = "获取约束条件(where)参数")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/view_value")
    public DataResult findViewValue(@RequestParam String mapId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findValueInViewColumn(mapId));
    }

    @ApiOperation(value = "获取数据", notes = "获取数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/view_data")
    public DataResult findViewData(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findViewData(form));
    }

    @ApiOperation(value = "获取多维数据", notes = "获取多维数据(field:3-周统计，2-月统计，1-年统计)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/view_cube_data")
    public DataResult findViewCubeData(@RequestBody CubeForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findViewCubeData(form));
    }
}
