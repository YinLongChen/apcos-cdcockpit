package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.list.CubeForm;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListForm;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListOperationVo;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.ResponseResult;
import com.jinxin.platform.cdcockpit.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Huang LingSong
 * 2020-05-18 11:32
 */
@RestController
@RequestMapping("/capsule/list")
@CrossOrigin
public class ListController {


    @Autowired
    private ListService listService;


    @GetMapping
    @Deprecated
    public DataResult findByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findByType(modelId));
    }

    @GetMapping("/map")
    @Deprecated
    public DataResult findNameMapByType(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findNameMapByType(modelId));
    }

    @GetMapping("/operation")
    public DataResult findOperationByModelId(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findOperationByModelId(modelId));
    }


    @GetMapping("/findColumn")
    public DataResult findColumn(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findColumnByModelId(modelId));
    }

    @Deprecated
    @GetMapping("/findValue")
    public DataResult findValue(@RequestParam String mapId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findValueInColumn(mapId));
    }

    @PostMapping("/data")
    @Deprecated
    public DataResult findData(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findData(form));
    }

    @GetMapping("/view_map")
    public DataResult findNameMapByModelId(@RequestParam String modelId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findNameMapByModelId(modelId));
    }

    @GetMapping("/view_value")
    public DataResult findViewValue(@RequestParam String mapId) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findValueInViewColumn(mapId));
    }

    @PostMapping("/view_data")
    public DataResult findViewData(@RequestBody ListForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findViewData(form));
    }

    @PostMapping("/view_cube_data")
    public DataResult findViewCubeData(@RequestBody CubeForm form) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", listService.findViewCubeData(form));
    }

    @PostMapping("/operation")
    public ResponseResult addOperation(@RequestBody ListOperationVo operationVo) {
        return listService.addOperation(operationVo) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }
}
