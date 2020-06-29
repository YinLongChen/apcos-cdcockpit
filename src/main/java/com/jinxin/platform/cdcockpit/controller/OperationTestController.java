package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.pojo.vo.user.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Huang LingSong
 * 2020-05-28 10:58
 */
@RestController
@RequestMapping("/capsule/operation-test")
public class OperationTestController {


    @GetMapping
    public DataResult get(@RequestParam String id, @RequestParam String content) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", id + content);
    }

    @PostMapping
    public DataResult post(@RequestBody LoginUser u) {
        return new DataResult<>(HttpStatus.OK.value(), "成功", u.getUserId() + u.getUserName());
    }
}
