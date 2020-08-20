package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domain.User;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.PagingResult;
import com.jinxin.platform.cdcockpit.pojo.vo.user.UserForm;
import com.jinxin.platform.cdcockpit.service.CountUserService;
import io.swagger.annotations.ApiOperation;
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
    @PostMapping("/findUsers")
    public PagingResult<User> findUsers(@RequestBody UserForm userForm) {
        return new PagingResult<>(HttpStatus.OK.value(), "成功", countUserService.findUsersPage(userForm));
    }

    @ApiOperation(value = "用户性别统计", notes = "用户性别统计")
    @GetMapping("/countByGender")
    public DataResult<List<CountResult>> countByGender() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByGender());
    }

    @ApiOperation(value = "用户年龄段统计", notes = "用户年龄段统计")
    @GetMapping("/countByAgeGroup")
    public DataResult<List<CountResult>> userCountByAgeGroup() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByAgeGroup());
    }

    @ApiOperation(value = "用户地区段统计", notes = "用户地区段统计")
    @GetMapping("/countByRegion")
    public DataResult<List<CountResult>> userCountByRegion() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByRegion());
    }


    @ApiOperation(value = "最近一天/一周/一月/一年注册用户统计", notes = "注册用户统计(5-天统计，3-周统计，2-月统计，1-年统计)")
    @GetMapping("/countByTime")
    public DataResult<List<CountResult>> userCountByTime(@RequestParam Integer field) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByTime(field));
    }

}
