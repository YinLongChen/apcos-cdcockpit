package com.jinxin.platform.apcos.data.capsule.pojo.vo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
