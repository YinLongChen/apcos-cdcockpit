package com.jinxin.platform.apcos.cockpit.service.impl;

import com.jinxin.platform.apcos.cockpit.mapper.ListMapper;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ListMap;
import com.jinxin.platform.apcos.cockpit.pojo.domain.ListOperation;
import com.jinxin.platform.apcos.cockpit.pojo.enumeration.DataType;
import com.jinxin.platform.apcos.cockpit.pojo.vo.list.ListForm;
import com.jinxin.platform.apcos.cockpit.pojo.vo.list.Where;
import com.jinxin.platform.apcos.cockpit.service.ListService;
import com.jinxin.platform.apcos.cockpit.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<Map<String, Object>> findByType(String modelId) {

        Map<String, String> map = listMapper.selectMap(modelId).stream().collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnEn));

        return transformColumnName(listMapper.selectByModelId(modelId), map);

    }

    @Override
    public Map<String, String> findNameMapByType(String modelId) {
        return listMapper.selectMap(modelId).stream().collect(Collectors.toMap(ListMap::getColumnEn, ListMap::getColumnCn));
    }

    @Override
    public List<ListOperation> findOperationByModelId(String modelId) {
        return listMapper.selectOpreation(modelId);
    }

    @Override
    public List<ListMap> findColumnByModelId(String modelId) {
        return listMapper.selectMap(modelId);
    }

    @Override
    public List<Object> findValueInColumn(String mapId) {
        List<Object> list;
        String column = getMap(mapId).getColumnOrl();
        try {
            list = listMapper.selectValueInColumn(column);
        } catch (Exception e) {
            throw new RuntimeException("无效的参数");
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findData(ListForm form) {
        if (StringUtils.isEmpty(form.getModelId())) {
            throw new RuntimeException("model_id不能为空");
        }
        String where = "where MODEL_ID = '" + form.getModelId() + "'";
        if (form.getWheres() != null && !form.getWheres().isEmpty()) {
            for (Where w : form.getWheres()) {
                ListMap listMap = getMap(w.getColumn());
                if ("COLUMN_15".equals(listMap.getColumnOrl()) || "COLUMN_16".equals(listMap.getColumnOrl())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date startTime;
                    try {
                        startTime = sdf.parse(w.getParam());
                    } catch (ParseException e) {
                        throw new RuntimeException("时间参数[" + w.getParam() + "]格式(yyyy-MM-dd HH:mm:ss)不正确");
                    }

                    where += " and " + listMap.getColumnOrl() + " >= to_date( '" + sdf.format(startTime) + "','yyyy-mm-dd hh24:mi:ss')";

                } else {
                    int index = findValueInColumn(w.getColumn()).indexOf(w.getParam());
                    if (index == -1) {
                        throw new RuntimeException("参数[" + w.getParam() + "]不存在");
                    }
                    where += " and " + listMap.getColumnOrl() + " = '" + w.getParam() + "'";
                }

            }
        }
        if (!StringUtils.isEmpty(form.getGroupBy())) {
            Map map = new HashMap<>();
            map.put("NAME", "name");
            map.put("VALUE", "value");
            return transformColumnName(listMapper.selectDataCount(getMap(form.getGroupBy()).getColumnOrl(), where), map);
        }
        Map<String, String> map = listMapper.selectMap(form.getModelId()).stream().collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnEn));
        return transformColumnName(listMapper.selectData(where), map);
    }

    @Override
    public List<Map<String, Object>> findViewData(ListForm form) {
        if (StringUtils.isEmpty(form.getModelId())) {
            throw new RuntimeException("model_id不能为空");
        }
        String view = listMapper.selectViewByModelId(form.getModelId());
        if (StringUtils.isEmpty(view)) {
            throw new RuntimeException("没有找到view");
        }
        String where = " where 1 = 1 ";
        if (form.getWheres() != null && !form.getWheres().isEmpty()) {
            for (Where w : form.getWheres()) {
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
                    int index = findValueInViewColumn(view, w.getColumn()).indexOf(w.getParam());
                    if (index == -1) {
                        throw new RuntimeException("参数[" + w.getParam() + "]不存在");
                    }
                    where += " and " + listMap.getColumnOrl() + " = '" + w.getParam() + "'";
                }
            }
        }
        if (!StringUtils.isEmpty(form.getGroupBy())) {
            return transformColumnName(listMapper.selectViewDataCount(view, getMap(form.getGroupBy()).getColumnOrl(), where));
        }
        return transformColumnName(listMapper.selectViewData(view, where));
    }

    @Override
    public Map<String, String> findNameMapByModelId(String modelId) {
        return listMapper.selectMap(modelId).stream()
                .map(u -> {
                    u.setColumnOrl(StringUtil.toFirstLetterUpper(u.getColumnOrl()));
                    return u;
                }).collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnCn));
    }

    public List<Object> findValueInViewColumn(String view, String mapId) {
        List<Object> list;
        String column = getMap(mapId).getColumnOrl();
        try {
            list = listMapper.selectValueInViewColumn(view, column);
        } catch (Exception e) {
            throw new RuntimeException("无效的参数");
        }
        return list;
    }

    private ListMap getMap(String mapId) {
        ListMap listMap = listMapper.getMapById(mapId);
        if (listMap == null) {
            throw new RuntimeException("找不到 map_id:[" + mapId + "]对应的记录");
        }
        return listMap;
    }

    private List<Map<String, Object>> transformColumnName(List<Map<String, Object>> data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> result = data.stream().map(u -> {
            Map<String, Object> temp = new HashMap<>();
            for (Map.Entry<String, Object> entry : u.entrySet()) {
                String nameEn = StringUtil.toFirstLetterUpper(entry.getKey());
                if (entry.getValue() instanceof Date) {
                    // 如果是时间类型格式化再输出
                    temp.put(nameEn, dateFormat.format((Date) entry.getValue()));
                } else {
                    temp.put(nameEn, entry.getValue());
                }
            }
            return temp;
        }).collect(Collectors.toList());
        return result;
    }

    private List<Map<String, Object>> transformColumnName(List<Map<String, Object>> data, Map<String, String> map) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> result = data.stream().map(u -> {
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

}
