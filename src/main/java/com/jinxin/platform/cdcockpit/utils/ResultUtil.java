package com.jinxin.platform.cdcockpit.utils;

import com.jinxin.platform.cdcockpit.pojo.domain.ListMap;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountStrResult;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Huang LingSong
 * 2020-06-22 15:09
 */
@Slf4j
public class ResultUtil {

    public static Calendar handleTime(Integer field) {
        Calendar c = Calendar.getInstance();
        switch (field) {
            case Calendar.DATE:
                c.add(Calendar.DATE, -1);
                break;
            case Calendar.WEEK_OF_YEAR:
                c.add(Calendar.DATE, -7);
                break;
            case Calendar.MONTH:
                c.add(Calendar.MONTH, -1);
                break;
            case Calendar.YEAR:
                c.add(Calendar.YEAR, -1);
                break;
            default:
                c.add(Calendar.DATE, -7);
        }
        return c;
    }

    public static SimpleDateFormat buildDateFormatStr(Integer field) {
        SimpleDateFormat dateFormat;
        switch (field) {
            case Calendar.DATE:
                dateFormat = new SimpleDateFormat("dd日HH点");
                break;
            case Calendar.WEEK_OF_YEAR:
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case Calendar.MONTH:
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case Calendar.YEAR:
                dateFormat = new SimpleDateFormat("yyyy-MM");
                break;
            default:
                return new SimpleDateFormat("MM-dd");

        }
        return dateFormat;
    }

    public static List<CountStrResult> fillInData(List<CountStrResult> data, Integer field) {
        //获取注册人数为零的日期 填充
        Calendar c = handleTime(field);
        SimpleDateFormat dateFormat = buildDateFormatStr(field);
        List<CountStrResult> fill = new ArrayList<>();
        while (c.getTime().before(new Date())) {
            CountStrResult countStrResult = CountStrResult.builder().name(dateFormat.format(c.getTime())).build();
            if (data.indexOf(countStrResult) == -1) {
                fill.add(CountStrResult.builder().name(dateFormat.format(c.getTime())).value("0").build());
            }
            switch (field) {
                case Calendar.MONTH:
                case Calendar.WEEK_OF_YEAR:
                    c.add(Calendar.DATE, 1);
                    break;
                case Calendar.YEAR:
                    c.add(Calendar.MONTH, 1);
                    break;
                default:
                    c.add(Calendar.DATE, 1);
            }
        }
        data.addAll(fill);
        data.sort(Comparator.comparing(CountStrResult::getName));
        return data;
    }


    public static List<Map<String, Object>> fillInData2(List<Map<String, Object>> data, Integer field) {
        //获取注册人数为零的日期 填充
        Calendar c = handleTime(field);

        SimpleDateFormat formater;
        if (Calendar.YEAR == field) {
            formater = new SimpleDateFormat("yyyy-MM-dd");
        } else if (Calendar.MONTH == field) {
            formater = new SimpleDateFormat("MM-dd");
        } else {
            throw new RuntimeException("field参数错误");
        }
        List<Map<String, Object>> fill = new ArrayList<>();
        while (c.getTime().before(new Date())) {
            String newTime = formater.format(c.getTime());
            if (indexOf(data, "TIME_STR", newTime) == -1) {
                Map<String, Object> map = new HashMap<>();
                map.put("TIME_STR", newTime);
                map.put("TIME", c.getTime());
                fill.add(map);
            }
            c.add(Calendar.DATE, 1);
        }

        data.addAll(fill);

        Collections.sort(data, Comparator.comparing(o -> ((String) o.get("TIME_STR"))));

        return data;
    }

    private static int indexOf(List<Map<String, Object>> data, String key, String value) {
        for (int i = 0; i < data.size(); i++) {
            if (value.equals(data.get(i).get(key))) {
                return i;
            }
        }
        return -1;
    }
}
