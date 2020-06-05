package com.jinxin.platform.apcos.cockpit.pojo.vo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Huang LingSong
 * 2020-03-26 10:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult implements Serializable {
    private Integer status;
    private String msg;
}
