package com.jinxin.platform.apcos.cockpit.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-04-26 9:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String idCard;
    private Integer age;
    private String ageGroup;
    private String gender;
    private String dept;
    private String prov;
    private String city;
    private String address;
    private String phone;
    private Date registTime;
    private String registStrTime;
}
