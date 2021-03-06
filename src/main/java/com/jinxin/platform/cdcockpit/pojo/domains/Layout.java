package com.jinxin.platform.cdcockpit.pojo.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-13 15:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Layout {
    private String id;
    private String userId;
    private String layout;
    private String style;
    private String colNum;
    private Integer status;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String name;
    private Integer order;
    private String logo;
}
