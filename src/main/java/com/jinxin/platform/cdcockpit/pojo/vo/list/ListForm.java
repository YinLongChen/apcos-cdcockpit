package com.jinxin.platform.cdcockpit.pojo.vo.list;

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

   private String order;

   /**
    * 当 groupBy 为时间类型时必传，3-周统计，2-月统计，1-年统计
    */
   private Integer field;

   private Integer current = 1;

   private Integer size = 10;

}
