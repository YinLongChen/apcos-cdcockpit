package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domains.User;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.PagingResult;
import com.jinxin.platform.cdcockpit.pojo.vo.user.UserForm;
import com.jinxin.platform.cdcockpit.service.CountUserService;
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
@CrossOrigin
public class CountUserController {

    @Autowired
    private CountUserService countUserService;

    @PostMapping("/findUsers")
    public PagingResult<User> findUsers(@RequestBody UserForm userForm) {
        return new PagingResult<>(HttpStatus.OK.value(), "成功", countUserService.findUsersPage(userForm));
    }

    @GetMapping("/countByGender")
    public DataResult<List<CountResult>> countByGender() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByGender());
    }

    @GetMapping("/countByAgeGroup")
    public DataResult<List<CountResult>> userCountByAgeGroup() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByAgeGroup());
    }

    @GetMapping("/countByRegion")
    public DataResult<List<CountResult>> userCountByRegion() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByRegion());
    }


    @GetMapping("/countByTime")
    public DataResult<List<CountResult>> userCountByTime(@RequestParam Integer field) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", countUserService.userCountByTime(field));
    }

}
