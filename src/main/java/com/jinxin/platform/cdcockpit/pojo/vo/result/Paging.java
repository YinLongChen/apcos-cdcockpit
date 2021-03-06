package com.jinxin.platform.cdcockpit.pojo.vo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-03-26 15:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paging<T> {
    private Integer current;
    private Integer size;
    private long total;
    private long pageTotal;
    private List<T> records;

    public Paging(Integer current, Integer size) {
        this.current = current;
        this.size = size;
    }

}
