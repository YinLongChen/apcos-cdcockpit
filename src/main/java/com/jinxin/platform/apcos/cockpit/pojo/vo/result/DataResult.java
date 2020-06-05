package com.jinxin.platform.apcos.cockpit.pojo.vo.result;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-03-31 9:33
 */
@Data
public class DataResult<T> extends ResponseResult {

    private T data;

    public DataResult(Integer status, String msg, T data) {
        super(status, msg);
        this.data = data;
    }
}
