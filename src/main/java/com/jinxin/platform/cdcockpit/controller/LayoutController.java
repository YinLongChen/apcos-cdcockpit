package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domains.CdcockpitTitle;
import com.jinxin.platform.cdcockpit.pojo.domains.Layout;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutCriteria;
import com.jinxin.platform.cdcockpit.pojo.vo.config.LayoutForm;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.ResponseResult;
import com.jinxin.platform.cdcockpit.pojo.vo.user.LoginUser;
import com.jinxin.platform.cdcockpit.service.CdcockpitTitleService;
import com.jinxin.platform.cdcockpit.service.CockpitSupportService;
import com.jinxin.platform.cdcockpit.service.LayoutService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/capsule/config")
@CrossOrigin
public class LayoutController {

    @Autowired
    private LayoutService layoutService;

    @Autowired
    private CdcockpitTitleService cdcockpitTitleService;

    @Autowired
    private CockpitSupportService cockpitSupportService;

    @PostMapping
    public ResponseResult add(@RequestBody LayoutForm layout) {
        return layoutService.add(layout) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }


    @PutMapping
    public ResponseResult edit(@RequestBody LayoutForm layout) {
        return layoutService.edit(layout) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }


    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable String id) {
        return layoutService.delete(id) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");
    }


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


    @PostMapping("/find")
    public DataResult<List<Layout>> find(@RequestBody LayoutCriteria layout) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", layoutService.find(layout));
    }

    @GetMapping("/project")
    public DataResult<CdcockpitTitle> getProName() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", cdcockpitTitleService.queryAll(new CdcockpitTitle()).get(0));
    }

    @PostMapping("/saveTitle")
    public ResponseResult saveTitle(@RequestBody  CdcockpitTitle cdcockpitTitle){
        List<CdcockpitTitle> cdcockpitTitles = cdcockpitTitleService.queryAll(new CdcockpitTitle());
        if (cdcockpitTitles.size()>0){
            CdcockpitTitle temp = cdcockpitTitles.get(0);
            BeanUtils.copyProperties(cdcockpitTitle,temp);
            cdcockpitTitleService.update(temp);
        }else {
            cdcockpitTitleService.insert(cdcockpitTitle);
        }
        return new ResponseResult(HttpStatus.OK.value(), "成功");
    }

    @PutMapping("/project")
    public ResponseResult updateProName(@RequestParam("name")String name) {
        return layoutService.updateProjectName(name) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");

    }

    @PutMapping("/logo")
    public ResponseResult updateLogo(@RequestParam("name")String url) {
        return layoutService.updateLogo(url) ?
                new ResponseResult(HttpStatus.OK.value(), "成功") : new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "失败");

    }


}
