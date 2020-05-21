package com.jinxin.platform.apcos.data.capsule.service.impl;

import com.jinxin.platform.apcos.data.capsule.mapper.ListMapper;
import com.jinxin.platform.apcos.data.capsule.pojo.domain.ListMap;
import com.jinxin.platform.apcos.data.capsule.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<Map<String, Object>> findByType(String type) {

        Map<String, String> map =  listMapper.selectMap(type).stream().collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnEn));

        List<Map<String, Object>> result = listMapper.selectByModelId(type).stream().map(u -> {
            Map<String, Object> temp = new HashMap<>();
            for (Map.Entry<String, Object> entry : u.entrySet()) {
                String nameEn = map.get(entry.getKey());
                temp.put(nameEn == null ? entry.getKey() : nameEn, entry.getValue());
            }
            return temp;
        }).collect(Collectors.toList());

        return result;
    }
//
//    @Override
//    public List<String> findDataType() {
//        return listMapper.selectDataType();
//    }

    @Override
    public Map<String, String> findNameMapByType(String type) {
        return listMapper.selectMap(type).stream().collect(Collectors.toMap(ListMap::getColumnEn, ListMap::getColumnCn));
    }
}



