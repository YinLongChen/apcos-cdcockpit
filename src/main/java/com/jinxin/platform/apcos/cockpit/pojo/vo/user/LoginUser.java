package com.jinxin.platform.apcos.cockpit.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang LingSong
 * 2020-04-26 9:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private String userId;
    private String userName;
}
