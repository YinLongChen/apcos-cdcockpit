package com.jinxin.platform.apcos.cockpit.pojo.vo.list;

import lombok.Data;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-05-22 10:36
 */
@Data
public class ListForm {

   private String modelId;

   private List<Where> wheres;
   /**
    * group by 字段(传map_id)
    */
   private String groupBy;

}
