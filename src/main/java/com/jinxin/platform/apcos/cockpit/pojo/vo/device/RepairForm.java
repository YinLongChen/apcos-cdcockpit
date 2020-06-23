package com.jinxin.platform.apcos.cockpit.pojo.vo.device;

import com.jinxin.platform.apcos.cockpit.pojo.vo.list.Where;
import lombok.Data;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-22 10:55
 */
@Data
public class RepairForm {

    private String xAxis;

    private List<Where> wheres;

    private Integer field;

    private  boolean ratio;
}
