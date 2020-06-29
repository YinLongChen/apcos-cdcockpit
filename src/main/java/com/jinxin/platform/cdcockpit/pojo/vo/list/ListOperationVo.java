package com.jinxin.platform.cdcockpit.pojo.vo.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-28 15:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOperationVo {
    private String id;
    private String modelId;
    private String name;
    private String url;
    private String method;
    private List<ListOperationParam> param;
}
