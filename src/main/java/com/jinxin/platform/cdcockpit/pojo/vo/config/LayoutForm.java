package com.jinxin.platform.cdcockpit.pojo.vo.config;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-05-13 16:19
 */
@Data
public class LayoutForm {
    private String id;
    private String name;
    private String layout;
    private String style;
    private String colNum;
    private Integer status;
    private String remark;
    private Integer order;
}
