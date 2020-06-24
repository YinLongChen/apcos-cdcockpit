package com.jinxin.platform.cdcockpit.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-05-21 16:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    private String province;
    private String city;
}
