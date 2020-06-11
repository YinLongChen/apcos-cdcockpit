package com.jinxin.platform.apcos.cockpit.service.impl;

import com.jinxin.platform.apcos.cockpit.mapper.ListMapper;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ListMap;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ListOperation;
import com.jinxin.platform.apcos.cockpit.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-05-18 11:28
 */
@Slf4j
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListMapper listMapper;

    @Override
    public List<Map<String, Object>> findByType(String modelId) {

        Map<String, String> map = listMapper.selectMap(modelId).stream().collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnEn));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> result = listMapper.selectByModelId(modelId).stream().map(u -> {
            Map<String, Object> temp = new HashMap<>();

            for (Map.Entry<String, Object> entry : u.entrySet()) {

                String nameEn = map.get(entry.getKey());

                if (entry.getValue() instanceof Date) {
                    // 如果是时间类型格式化再输出
                    temp.put(nameEn == null ? entry.getKey() : nameEn, dateFormat.format((Date) entry.getValue()));
                } else {
                    temp.put(nameEn == null ? entry.getKey() : nameEn, entry.getValue());
                }

            }
            return temp;
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public Map<String, String> findNameMapByType(String modelId) {
        return listMapper.selectMap(modelId).stream().collect(Collectors.toMap(ListMap::getColumnEn, ListMap::getColumnCn));
    }

    @Override
    public List<ListOperation> findOperationByModelId(String modelId) {
        return listMapper.selectOpreation(modelId);
    }

//    @Override
//    public List<Map<String, Object>> findReportByType(String modelId, Integer field) {
//        Calendar c = Calendar.getInstance();
//        switch (field) {
//            case Calendar.DATE:
//                c.add(Calendar.DATE, -1);
//                break;
//            case Calendar.WEEK_OF_YEAR:
//                c.add(Calendar.DATE, -7);
//                break;
//            case Calendar.MONTH:
//                c.add(Calendar.MONTH, -1);
//                break;
//            case Calendar.YEAR:
//                c.add(Calendar.YEAR, -1);
//                break;
//            default:
//                c.add(Calendar.DATE, -1);
//        }
//
//        Map<String, String> map = listMapper.selectMap(modelId).stream().collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnEn));
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<Map<String, Object>> result = listMapper.selectByModelId(modelId).stream()
//                .filter(u -> u.get("COLUMN_15") != null && c.getTime().before((Date) u.get("COLUMN_15")))
//                .map(u -> {
//                    Map<String, Object> temp = new HashMap<>();
//
//                    for (Map.Entry<String, Object> entry : u.entrySet()) {
//
//                        String nameEn = map.get(entry.getKey());
//
//                        if (entry.getValue() instanceof Date) {
//                            // 如果是时间类型格式化再输出
//                            temp.put(nameEn == null ? entry.getKey() : nameEn, dateFormat.format((Date) entry.getValue()));
//                        } else {
//                            temp.put(nameEn == null ? entry.getKey() : nameEn, entry.getValue());
//                        }
//
//                    }
//                    return temp;
//                }).collect(Collectors.toList());
//
//        return result;
//    }
}



