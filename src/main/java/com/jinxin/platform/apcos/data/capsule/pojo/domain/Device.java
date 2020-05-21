package com.jinxin.platform.apcos.data.capsule.pojo.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-20 10:55
 */
@Data
public class Device {
    private String deviceNum;
    private String deviceMac;
    private String produceCode;
    private Date registTime;
    private String registStrTime;
    private String address;
    private String phone;
    private String prov;
    private String city;
}
