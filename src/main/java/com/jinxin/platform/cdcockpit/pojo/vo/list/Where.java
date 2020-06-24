package com.jinxin.platform.cdcockpit.pojo.vo.list;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-06-15 9:46
 */
@Data
public class Where {
    /**
     * where 字段(传map_id)
     */
    private String column;


    private String param;

}
