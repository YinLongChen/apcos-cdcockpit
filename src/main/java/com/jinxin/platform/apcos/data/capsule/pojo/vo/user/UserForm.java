package com.jinxin.platform.apcos.data.capsule.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-15 10:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String ageGroup;
    private String gender;
    private Date startTime;
    private Integer current = 1;
    private Integer size = 10;
}
