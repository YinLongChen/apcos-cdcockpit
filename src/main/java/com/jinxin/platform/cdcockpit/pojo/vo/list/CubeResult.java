package com.jinxin.platform.cdcockpit.pojo.vo.list;

import com.jinxin.platform.cdcockpit.pojo.vo.config.Series;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Huang LingSong
 * 2020-06-18 14:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CubeResult {
    private List<String> xAxis;
    private List<Series> series;
}
