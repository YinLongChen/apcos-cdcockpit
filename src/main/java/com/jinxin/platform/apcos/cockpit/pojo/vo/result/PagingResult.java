package com.jinxin.platform.apcos.cockpit.pojo.vo.result;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-03-26 15:18
 */
@Data
public class PagingResult<T> extends ResponseResult {

    private Paging<T> paging;

    public PagingResult(Integer status, String msg, Paging<T> paging) {
        super(status, msg);
        this.paging = paging;
    }
}
