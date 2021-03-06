package com.jinxin.platform.cdcockpit.controller;

import com.jinxin.platform.cdcockpit.pojo.domains.LinkModel;
import com.jinxin.platform.cdcockpit.pojo.vo.result.DataResult;
import com.jinxin.platform.cdcockpit.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-03 14:08
 */
@RestController
@RequestMapping("/capsule/link")
@CrossOrigin
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping
    public DataResult<List<LinkModel>> find() {
        return new DataResult<>(HttpStatus.OK.value(), "成功", linkService.findAll());
    }
}
