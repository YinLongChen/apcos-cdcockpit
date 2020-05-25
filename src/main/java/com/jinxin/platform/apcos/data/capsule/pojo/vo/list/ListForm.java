package com.jinxin.platform.apcos.data.capsule.pojo.vo.list;

import lombok.Data;

/**
 * @author Huang LingSong
 * 2020-05-22 10:36
 */
@Data
public class ListForm {
   private Integer current = 1;
   private Integer size = 10;
   private String type;
}
