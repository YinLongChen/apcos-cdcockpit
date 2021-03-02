package com.jinxin.platform.cdcockpit.pojo.domains;

import lombok.Data;

import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-20 10:55
 */
@Data
public class Device {
    private String serialNum;
    private String deviceMac;
    private String productCode;
    private String productCodeName;
    private Date registTime;
    private String registStrTime;
    private String address;
    private String phone;
    private String prov;
    private String city;
}
