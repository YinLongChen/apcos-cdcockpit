package com.jinxin.platform.apcos.cockpit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxin.platform.apcos.cockpit.mapper.DeviceMapper;
import com.jinxin.platform.apcos.cockpit.mapper.ListMapper;
import com.jinxin.platform.apcos.cockpit.pojo.domain.Device;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ListMap;
import com.jinxin.platform.apcos.cockpit.pojo.enumeration.DataType;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.config.CountStrResult;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.DeviceCriteria;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.DeviceForm;
import com.jinxin.platform.apcos.cockpit.pojo.vo.device.RepairForm;
import com.jinxin.platform.apcos.cockpit.pojo.vo.list.Where;
import com.jinxin.platform.apcos.cockpit.pojo.vo.result.Paging;
import com.jinxin.platform.apcos.cockpit.service.DeviceService;
import com.jinxin.platform.apcos.cockpit.utils.Phone;
import com.jinxin.platform.apcos.cockpit.utils.PhoneUtil;
import com.jinxin.platform.apcos.cockpit.utils.ResultUtil;
import com.jinxin.platform.apcos.cockpit.utils.StringUtil;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
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

    @Autowired
    private ListMapper listMapper;


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
        DeviceCriteria deviceCriteria = DeviceCriteria
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

    @Override
    public List<CountResult> deviceCountByProductCode() {
        List<Device> devices = deviceMapper.selectDevice(DeviceCriteria.builder().build());
        Map<String, List<Device>> map = devices.stream().collect(Collectors.groupingBy(Device::getProductCode));

        List<CountResult> countResults = new ArrayList<>();
        for (Map.Entry<String, List<Device>> entry : map.entrySet()) {
            String name = entry.getValue().get(0).getProductCodeName();
            name = StringUtils.isEmpty(name) ? entry.getKey() : name;
            countResults.add(CountResult.builder().name(name).value(entry.getValue().size()).build());

        }

        return countResults;
    }

    @Override
    public CountResult sumDev() {
        return CountResult.builder()
                .name("sum_dev")
                .value(deviceMapper.sumDev())
                .build();
    }

    @Override
    public List<CountStrResult> deviceRepair(RepairForm form) {
        String where = buildWhere(form.getWheres());
        if (StringUtils.isEmpty(form.getXAxis())) {
            throw new RuntimeException("Group By不能为空");
        }

        ListMap listMap = getMap(form.getXAxis());


        if (DataType.DATE.getValue().equals(listMap.getDataType())) {
            Calendar c = ResultUtil.handleTime(form.getField());

            SimpleDateFormat dateFormat = ResultUtil.buildDateFormatStr(form.getField());
            Map<String, List<CountStrResult>> dataMap = deviceMapper.selectViewData(where).stream()
                    .filter(f -> !StringUtils.isEmpty(f.get(listMap.getColumnOrl())))
                    .filter(f -> c.getTime().before((Date) f.get(listMap.getColumnOrl())))
                    .map(u -> CountStrResult.builder().name(dateFormat.format(u.get(listMap.getColumnOrl()))).build())
                    .collect(Collectors.groupingBy(CountStrResult::getName));

            List<CountStrResult> countStrResults = new ArrayList<>();

            for (Map.Entry<String, List<CountStrResult>> entry : dataMap.entrySet()) {
                countStrResults.add(CountStrResult.builder().name(entry.getKey()).value(String.valueOf(entry.getValue().size())).build());
            }

            if (form.isRatio()) {
                Integer sum = deviceMapper.sumDev();
                countStrResults = countStrResults.stream().map(m -> {
                    double ratio = Double.parseDouble(m.getValue()) / sum;
                    return CountStrResult.builder().name(m.getName()).value(String.format("%.2f", ratio)).build();
                }).collect(Collectors.toList());
            }

            return ResultUtil.fillInData(countStrResults, form.getField());
        } else {
            List<CountResult> countResults = deviceMapper.selectViewDataCount(listMap.getColumnOrl(), where);
            if (form.isRatio()) {
                int sum = countResults.stream().mapToInt(CountResult::getValue).sum();
                return countResults.stream()
                        .map(m -> {
                            double ratio = Double.parseDouble(m.getValue().toString()) / sum;
                            return CountStrResult.builder().name(m.getName()).value(String.format("%.2f", ratio)).build();
                        }).collect(Collectors.toList());
            }
            return countResults.stream()
                    .map(m -> CountStrResult.builder().name(m.getName()).value(m.getValue().toString()).build()
                    ).collect(Collectors.toList());
        }

    }

    private String buildWhere(List<Where> wheres) {
        String where = " where 1 = 1 ";
        if (wheres != null && !wheres.isEmpty()) {
            for (Where w : wheres) {
                ListMap listMap = getMap(w.getColumn());
                if (DataType.DATE.getValue().equals(listMap.getDataType())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date startTime;
                    try {
                        startTime = sdf.parse(w.getParam());
                    } catch (ParseException e) {
                        throw new RuntimeException("时间参数[" + w.getParam() + "]格式(yyyy-MM-dd HH:mm:ss)不正确");
                    }

                    where += " and " + listMap.getColumnOrl() + " >= to_date( '" + sdf.format(startTime) + "','yyyy-mm-dd hh24:mi:ss')";
                } else {
                    int index = findValueInViewColumn(listMap.getColumnOrl()).indexOf(w.getParam());
                    if (index == -1) {
                        throw new RuntimeException("参数[" + w.getParam() + "]不存在");
                    }
                    where += " and " + listMap.getColumnOrl() + " = '" + w.getParam() + "'";
                }
            }
        }
        return where;
    }

    private ListMap getMap(String mapId) {
        ListMap listMap = listMapper.getMapById(mapId);
        if (listMap == null) {
            throw new RuntimeException("找不到 map_id:[" + mapId + "]对应的记录");
        }
        return listMap;
    }

    private List<String> findValueInViewColumn(String column) {
        List<String> list;
        try {
            list = deviceMapper.selectValueInColumn(column);
        } catch (Exception e) {
            throw new RuntimeException("无效的参数");
        }
        return list;
    }

}
