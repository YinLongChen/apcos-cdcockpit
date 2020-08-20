package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.list.CubeForm;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListForm;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListOperationVo;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.ResponseResult;
import com.jinxin.platform.cdcockpit.service.ListService;
import io.swagger.annotations.ApiOperation;
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
    @GetMapping
    @Deprecated
    public DataResult findByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findByType(modelId));
    }

    @ApiOperation(value = "根据业务类型获取字段名中英文映射关系", notes = "根据业务类型获取字段名中英文映射关系")
    @GetMapping("/map")
    @Deprecated
    public DataResult findNameMapByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findNameMapByType(modelId));
    }

    @ApiOperation(value = "根据业务类型获取数据表格操作", notes = "根据业务类型获取数据表格操作")
    @GetMapping("/operation")
    public DataResult findOperationByModelId(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findOperationByModelId(modelId));
    }


    @ApiOperation(value = "获取约束条件(where/group by)", notes = "获取约束条件(where/group by)")
    @GetMapping("/findColumn")
    public DataResult findColumn(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findColumnByModelId(modelId));
    }

    @ApiOperation(value = "获取约束条件(where)参数", notes = "获取约束条件(where)参数")
    @Deprecated
    @GetMapping("/findValue")
    public DataResult findValue(@RequestParam String mapId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findValueInColumn(mapId));
    }

    @ApiOperation(value = "获取数据", notes = "获取数据")
    @PostMapping("/data")
    @Deprecated
    public DataResult findData(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findData(form));
    }

    @ApiOperation(value = "根据业务类型获取字段名中英文映射关系", notes = "根据业务类型获取字段名中英文映射关系")
    @GetMapping("/view_map")
    public DataResult findNameMapByModelId(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findNameMapByModelId(modelId));
    }

    @ApiOperation(value = "获取约束条件(where)参数", notes = "获取约束条件(where)参数")
    @GetMapping("/view_value")
    public DataResult findViewValue(@RequestParam String mapId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findValueInViewColumn(mapId));
    }

    @ApiOperation(value = "获取数据", notes = "当 groupBy 为时间类型时 field 必传，3-周统计，2-月统计，1-年统计,当所传field不符合要求时按周统计")
    @PostMapping("/view_data")
    public DataResult findViewData(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findViewData(form));
    }

    @ApiOperation(value = "获取多维数据", notes = "获取多维数据(field:3-周统计，2-月统计，1-年统计)")
    @PostMapping("/view_cube_data")
    public DataResult findViewCubeData(@RequestBody CubeForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findViewCubeData(form));
    }

    @ApiOperation(value = "添加表格操作", notes = "添加表格操作")
    @PostMapping("/operation")
    public ResponseResult addOperation(@RequestBody ListOperationVo operationVo) {
        return listService.addOperation(operationVo) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }
}
