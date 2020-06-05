package com.jinxin.platform.apcos.cockpit.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-15 10:01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria {
    private Integer ageStart;
    private Integer ageEnd;
    private String gender;
    private Date startTime;
}
