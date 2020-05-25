package com.jinxin.platform.apcos.data.capsule.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxin.platform.apcos.data.capsule.mapper.DeviceMapper;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.Device;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.device.DeviceCriteria;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.device.DeviceForm;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.result.Paging;
import com.jinxin.platform.apcos.data.capsule.service.DeviceService;
import com.jinxin.platform.apcos.data.capsule.utils.Phone;
import com.jinxin.platform.apcos.data.capsule.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-05-20 11:07
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Paging<Device> findDevicePage(DeviceForm form) {
        Page page = PageHelper.startPage(form.getCurrent(), form.getSize());
        List<Device> devices = deviceMapper.selectDevice(DeviceCriteria.builder()
                .productCode(form.getProductCode())
                .startTime(form.getStartTime())
                .build());
        return new Paging<>(form.getCurrent(), form.getSize(), page.getTotal(), page.getPages(), devices);
    }

    @Override
    public List<CountResult> deviceCountByTime(int field) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat;
        switch (field) {
            case Calendar.DATE:
                c.add(Calendar.DATE, -1);
                dateFormat = new SimpleDateFormat("dd日HH点");
                break;
            case Calendar.WEEK_OF_YEAR:
                c.add(Calendar.DATE, -7);
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case Calendar.MONTH:
                c.add(Calendar.MONTH, -1);
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case Calendar.YEAR:
                c.add(Calendar.YEAR, -1);
                dateFormat = new SimpleDateFormat("yyyy-MM");
                break;
            default:
                return null;

        }
        DeviceCriteria deviceCriteria =  DeviceCriteria
                .builder()
                .startTime(c.getTime()).build();
        //获取数据
        List<Device> users = deviceMapper.selectDevice(
                deviceCriteria
        );

        Map<String, List<Device>> deviceMap = users.stream()
                .filter(u -> u.getRegistTime() != null)
                .map(u -> {
                    u.setRegistStrTime(dateFormat.format(u.getRegistTime()));
                    return u;
                })
                .collect(Collectors.groupingBy(Device::getRegistStrTime));

        List<CountResult> countResults = new ArrayList<>();
        Map<String, Integer> dataSet = new HashMap();
        for (Map.Entry<String, List<Device>> entry : deviceMap.entrySet()) {
            countResults.add(CountResult.builder().name(entry.getKey()).value(entry.getValue().size()).build());
            dataSet.put(entry.getKey(), entry.getValue().size());
        }

        //获取注册人数为零的日期 填充
        List<CountResult> fill = new ArrayList<>();
        while (c.getTime().before(new Date())) {
            if (null == dataSet.get(dateFormat.format(c.getTime()))) {
                fill.add(CountResult.builder().name(dateFormat.format(c.getTime())).value(0).build());
            }
            switch (field) {
                case Calendar.DATE:
                    c.add(Calendar.HOUR, 1);
                    break;
                case Calendar.MONTH:
                case Calendar.WEEK_OF_YEAR:
                    c.add(Calendar.DATE, 1);
                    break;
                case Calendar.YEAR:
                    c.add(Calendar.MONTH, 1);
                    break;
                default:
                    return null;

            }
        }

        countResults.addAll(fill);
        countResults.sort(Comparator.comparing(CountResult::getName));

        return countResults;
    }

    @Override
    public List<CountResult> deviceCountByRegion() {
        List<Device> devices = deviceMapper.selectDevice(DeviceCriteria.builder().build());

        Map<String, List<Device>> map = devices.stream().map(u -> {

            if (StringUtils.isEmpty(u.getAddress())) {
                Phone region = PhoneUtil.getPhoneNumberInfo(u.getPhone());
                u.setProv(region == null ? "未知" : region.getProvince());
            } else {
                u.setProv(PhoneUtil.transform(u.getAddress()).getProvince());
            }

            return u;
        }).collect(Collectors.groupingBy(Device::getProv));


        List<CountResult> countResults = new ArrayList<>();
        for (Map.Entry<String, List<Device>> entry : map.entrySet()) {

            countResults.add(CountResult.builder().name(entry.getKey()).value(entry.getValue().size()).build());

        }

        return countResults;
    }
}
