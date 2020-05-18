package com.jinxin.platform.apcos.data.capsule.controller;

import com.jinxin.platform.apcos.data.capsule.pojo.domain.User;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.result.DataResult;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.result.PagingResult;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.user.UserForm;
import com.jinxin.platform.apcos.data.capsule.service.CountUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-14 8:53
 */
@RestController
@RequestMapping("/capsule/user")
public class CountUserController {

    @Autowired
    private CountUserService countUserService;

    @ApiOperation(value = "用户查询", notes = "用户查询")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/findUsers")
    public PagingResult<User> findUsers(@RequestBody UserForm userForm) {
        return new PagingResult<>(HttpStatus.OK.value(), "成功", countUserService.findUsersPage(userForm));
    }

    @ApiOperation(value = "用户性别统计", notes = "用户性别统计")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByGender")
    public DataResult<List<CountResult>> countByGender() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByGender());
    }

    @ApiOperation(value = "用户年龄段统计", notes = "用户年龄段统计")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByAgeGroup")
    public DataResult<List<CountResult>> userCountByAgeGroup() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByAgeGroup());
    }

    @ApiOperation(value = "用户地区段统计", notes = "用户地区段统计")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByRegion")
    public DataResult<List<CountResult>> userCountByRegion() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByRegion());
    }


    @ApiOperation(value = "最近一月注册用户统计", notes = "最近一月注册用户统计")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByLastMonth")
    public DataResult<List<CountResult>> userCountByLastMonth() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByLastMonth());
    }

    @ApiOperation(value = "最近一年注册用户统计", notes = "最近一年注册用户统计")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @GetMapping("/countByLastYear")
    public DataResult<List<CountResult>> userCountByLastYear() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByLastYear());
    }

}
