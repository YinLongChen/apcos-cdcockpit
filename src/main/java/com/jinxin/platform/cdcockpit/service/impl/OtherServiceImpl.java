package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.mapper.OtherMapper;
import com.jinxin.platform.cdcockpit.service.CockpitSupportService;
import com.jinxin.platform.cdcockpit.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Slf4j
@Service
public class OtherServiceImpl implements OtherService {


    @Autowired
    private OtherMapper otherMapper;

    @Autowired
    private CockpitSupportService supportService;


    @Override
    public Integer selectQuota(String type, Integer feild) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String orgId = supportService.getWebCurrUser().getOrgId();
        if (feild.equals(Calendar.MONTH)) {
            LocalDate localDate = LocalDate.now();
            String date = localDate.format(formatter);
            return otherMapper.selectQuota(orgId, date, type);
        } else if (feild.equals(Calendar.YEAR)) {
            LocalDate localDate = LocalDate.now().minusYears(1L);
            Integer sum = 0;
            do {
                Integer num = otherMapper.selectQuota(orgId, localDate.format(formatter), type);
                sum += num == null ? 0 : num;
                localDate = localDate.plusMonths(1L);
            } while (localDate.isBefore(LocalDate.now().plusMonths(1L)));
            return sum;
        } else {
            throw new RuntimeException("参数feild错误");
        }
    }
}
