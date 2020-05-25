package com.jinxin.platform.apcos.data.capsule.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Huang LingSong
 * 2020-05-21 14:50
 */
@Slf4j
public class PhoneUtil {
    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();
    /**
     * 直辖市
     */
    private final static String[] MUNICIPALITY = {"北京市", "天津市", "上海市", "重庆市"};

    /**
     * 自治区
     */
    private final static String[] AUTONOMOUS_REGION = {"新疆", "内蒙古", "西藏", "宁夏", "广西"};

    /**
     * 根据国家代码和手机号 判断手机号是否有效
     *
     * @param phoneNumber 手机号码
     * @param countryCode 国号(区号)
     * @return true / false
     */
    public static boolean checkPhoneNumber(long phoneNumber, int countryCode) {
        Phonenumber.PhoneNumber pn = new Phonenumber.PhoneNumber();
        pn.setCountryCode(countryCode);
        pn.setNationalNumber(phoneNumber);
        return phoneNumberUtil.isValidNumber(pn);
    }

    private static Pattern phoneReg = Pattern.compile("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$");

    public static Phone getPhoneNumberInfo(String phoneNumber) {
        // 正则校验
        Matcher matcher = phoneReg.matcher("+86" + phoneNumber);
        if (!matcher.find()) {
            log.error("The phone number maybe like:" + "+[National Number][Phone number], but got {}", phoneNumber);
            return null;
        }

        Phonenumber.PhoneNumber referencePhonenumber;
        try {
            String language = "CN";
            referencePhonenumber = phoneNumberUtil.parse(phoneNumber, language);
        } catch (NumberParseException e) {
            log.error("NumberParseException:{}", e.getMessage());
            return null;

        }

        boolean checkSuccess = PhoneUtil.checkPhoneNumber(referencePhonenumber.getNationalNumber(), referencePhonenumber.getCountryCode());
        if (!checkSuccess) {
            log.error("Not an active number:{}", phoneNumber);
            return null;
        }
        String description = geocoder.getDescriptionForNumber(referencePhonenumber, Locale.CHINA);


        return transform(description);

    }

    public static Phone transform(String addr) {
        String[] splitArr = addr.split("省");

        if (splitArr.length==1){
            splitArr = addr.split("市");
        }

        Map<String, String> map = new HashMap<>();

        // 直辖市
        for (String val : MUNICIPALITY) {
            if (addr.equals(val)) {
                return Phone.builder()
                        .province(val.replace("市", ""))
                        .city(val)
                        .build();
            }
        }
        // 自治区
        for (String val : AUTONOMOUS_REGION) {
            if (addr.startsWith(val)) {
                return Phone.builder()
                        .province(val)
                        .city(addr.replace(val, ""))
                        .build();
            }
        }

        if (splitArr.length == 2) {
            return Phone.builder()
                    .province(splitArr[0])
                    .city(splitArr[1])
                    .build();

        } else {
            return Phone.builder()
                    .province(addr)
                    .city("")
                    .build();

        }

    }
}
