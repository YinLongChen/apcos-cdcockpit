package com.jinxin.platform.apcos.data.capsule.service.impl;

import com.jinxin.platform.apcos.data.capsule.mapper.DeviceReportMapper;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.DeviceReport;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.config.CountResult;
import com.jinxin.platform.apcos.data.capsule.pojo.vo.device.ReportCriteria;
import com.jinxin.platform.apcos.data.capsule.service.DeviceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-05-19 16:25
 */
@Service
public class DeviceReportServiceImpl implements DeviceReportService {

    @Autowired
    private DeviceReportMapper reportMapper;

    @Override
    public List<String> findReportType() {
        return reportMapper.selectReportType();
    }

    @Override
    public List<CountResult> reportCountByTime(int field,String type) {
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
        //获取数据
        List<DeviceReport> reports = reportMapper.selectReport(ReportCriteria.builder()
                .startTime(c.getTime())
//                .modelId(modelId)
                .cmdName(type)
                .build());

        Map<String, List<DeviceReport>> userMap = reports.stream()
                .filter(u -> u.getReportTime() != null)
                .map(u -> {

                    u.setReportStrTime(dateFormat.format(u.getReportTime()));
                    return u;
                })
                .collect(Collectors.groupingBy(DeviceReport::getReportStrTime));


        List<CountResult> countResults = new ArrayList<>();
        Map<String, Integer> dataSet = new HashMap();
        for (Map.Entry<String, List<DeviceReport>> entry : userMap.entrySet()) {
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
}
