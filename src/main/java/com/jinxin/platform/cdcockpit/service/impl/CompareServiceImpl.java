package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.exception.CockpitException;
import com.jinxin.platform.cdcockpit.mapper.CompareMapper;
import com.jinxin.platform.cdcockpit.pojo.domains.CompareModel;
import com.jinxin.platform.cdcockpit.pojo.vo.compare.CompareResult;
import com.jinxin.platform.cdcockpit.service.CompareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Huang LingSong
 * 2020-05-28 10:57
 */
@Slf4j
@Service
public class CompareServiceImpl implements CompareService {

    @Resource
    private CompareMapper compareMapper;


    @Override
    public CompareResult compareByModelId(String modelId, String month) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date param;
        try {
            param = dateFormat.parse(month);
        } catch (ParseException e) {
            throw new CockpitException("参数格式错误[" + month + "]");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(param);
        CompareModel baseMonth = compareMapper.selectCompareDataByModelIdAndMonth(modelId, dateFormat.format(c.getTime()));


        c.setTime(param);
        c.add(Calendar.MONTH, -1);
        CompareModel lastMonth = compareMapper.selectCompareDataByModelIdAndMonth(modelId, dateFormat.format(c.getTime()));

        c.setTime(param);
        c.add(Calendar.YEAR, -1);
        CompareModel lastYear = compareMapper.selectCompareDataByModelIdAndMonth(modelId, dateFormat.format(c.getTime()));

        c.setTime(param);
        return CompareResult.builder()
                .yearOnYear(calculation(baseMonth, lastYear))
                .linkRatio(calculation(baseMonth, lastMonth))
                .time(dateFormat.format(c.getTime()))
                .value(baseMonth == null ? 0 : baseMonth.getValue())
                .unit(baseMonth == null ? "" : baseMonth.getUnit())
                .total(compareMapper.countTatalByModelId(modelId))
                .build();
    }

    private String calculation(CompareModel molecule, CompareModel denominator) {
        if (molecule == null || denominator == null) {
            return "--";
        }
        if (molecule.getValue() == null || denominator.getValue() == null) {
            return "--";
        }
        double d = (Double.parseDouble(molecule.getValue().toString()) / denominator.getValue() - 1) * 100;
        return String.format("%.2f", d) + "%";

    }
}
