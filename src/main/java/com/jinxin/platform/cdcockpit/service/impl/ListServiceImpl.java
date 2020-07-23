package com.jinxin.platform.cdcockpit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jinxin.platform.cdcockpit.exception.CockpitException;
import com.jinxin.platform.cdcockpit.mapper.ListMapper;
import com.jinxin.platform.cdcockpit.pojo.domain.ListMap;
import com.jinxin.platform.cdcockpit.pojo.domain.ListOperation;
import com.jinxin.platform.cdcockpit.pojo.enumeration.DataType;
import com.jinxin.platform.cdcockpit.pojo.enumeration.ShowType;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountStrResult;
import com.jinxin.platform.cdcockpit.pojo.vo.config.Series;
import com.jinxin.platform.cdcockpit.pojo.vo.list.*;
import com.jinxin.platform.cdcockpit.service.ListService;
import com.jinxin.platform.cdcockpit.utils.ResultUtil;
import com.jinxin.platform.cdcockpit.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
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

        return transformColumnName(listMapper.selectByModelId(modelId), map);

    }

    @Override
    public Map<String, String> findNameMapByType(String modelId) {
        return listMapper.selectMap(modelId).stream().collect(Collectors.toMap(ListMap::getColumnEn, ListMap::getColumnCn));
    }

    @Override
    public boolean addOperation(ListOperationVo operationVo) {
        ListOperation operation = new ListOperation();
        BeanUtils.copyProperties(operationVo, operation);
        operation.setParam(JSON.toJSONString(operationVo.getParam()));
        return listMapper.saveOpreation(operation);
    }

    @Override
    public List<ListOperationVo> findOperationByModelId(String modelId) {
        final ListOperationVo[] operationVo = {null};
        return listMapper.selectOpreation(modelId).stream().map(m -> {
            operationVo[0] = new ListOperationVo();
            BeanUtils.copyProperties(m, operationVo[0]);
            List<ListOperationParam> params = JSONObject.parseArray(m.getParam(), ListOperationParam.class);
            if (params == null) {
                operationVo[0].setParam(new ArrayList<>());
            } else {
                operationVo[0].setParam(params);
            }
            return operationVo[0];
        }).collect(Collectors.toList());
    }

    @Override
    public List<ListMap> findColumnByModelId(String modelId) {
        return listMapper.selectMaps(ListMapCriteria.builder().modelId(modelId).show(ShowType.SHOW.getValue()).build());
    }

    @Override
    public List<Object> findValueInColumn(String mapId) {
        List<Object> list;
        String column = getMap(mapId).getColumnOrl();
        try {
            list = listMapper.selectValueInColumn(column);
        } catch (Exception e) {
            throw new CockpitException("无效的参数");
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findData(ListForm form) {
        if (StringUtils.isEmpty(form.getModelId())) {
            throw new CockpitException("model_id不能为空");
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
                        throw new CockpitException("时间参数[" + w.getParam() + "]格式(yyyy-MM-dd HH:mm:ss)不正确");
                    }

                    where += " and " + listMap.getColumnOrl() + " >= to_date( '" + sdf.format(startTime) + "','yyyy-mm-dd hh24:mi:ss')";

                } else {
                    int index = findValueInColumn(w.getColumn()).indexOf(w.getParam());
                    if (index == -1) {
                        throw new CockpitException("参数[" + w.getParam() + "]不存在");
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
            throw new CockpitException("model_id不能为空");
        }

        String view = listMapper.selectViewByModelId(form.getModelId());
        if (StringUtils.isEmpty(view)) {
            throw new CockpitException("没有找到view");
        }

        String where = buildWhere(form.getWheres(), view);

        if (StringUtils.isEmpty(form.getGroupBy())) {
            //列表模型
            String order = buildOrder(form.getOrder());
            PageHelper.startPage(form.getCurrent(), form.getSize());
            return transformColumnName(listMapper.selectViewData(view, where + order));
        }

        ListMap listMap = getMap(form.getGroupBy());
        if (DataType.DATE.getValue().equals(listMap.getDataType())) {
            //x轴为 时间 的 非列表模型
            if (form.getField() == null) {
                throw new CockpitException("当groupBy为时间类型时field必传");
            }
            Calendar c = ResultUtil.handleTime(form.getField());
            SimpleDateFormat dateFormat = ResultUtil.buildDateFormatStr(form.getField());

            Map<String, List<CountStrResult>> dataMap = listMapper.selectViewData(view, where).stream()
                    .filter(f -> !StringUtils.isEmpty(f.get(listMap.getColumnOrl())))
                    .filter(f -> c.getTime().before((Date) f.get(listMap.getColumnOrl())))
                    .map(u -> CountStrResult.builder().name(dateFormat.format(u.get(listMap.getColumnOrl()))).build())
                    .collect(Collectors.groupingBy(CountStrResult::getName));

            List<CountStrResult> countStrResults = new ArrayList<>();
            for (Map.Entry<String, List<CountStrResult>> entry : dataMap.entrySet()) {
                countStrResults.add(CountStrResult.builder().name(entry.getKey()).value(String.valueOf(entry.getValue().size())).build());
            }
            return ResultUtil.fillInData(countStrResults, form.getField()).stream()
                    .map(m -> JSONObject.parseObject(JSON.toJSONString(m)))
                    .collect(Collectors.toList());
        } else {
            //x轴为 不为时间 的 非列表模型
            return transformColumnName(listMapper.selectViewDataCount(view, listMap.getColumnOrl(), where));
        }
    }

    @Override
    public CubeResult findViewCubeData(CubeForm form) {
        if (StringUtils.isEmpty(form.getModelId())) {
            throw new CockpitException("model_id不能为空");
        }
        String view = listMapper.selectViewByModelId(form.getModelId());
        if (StringUtils.isEmpty(view)) {
            throw new CockpitException("没有找到view");
        }
        //维度
        ListMap cubeMap = getMap(form.getCube());
        if (DataType.DATE.getValue().equals(cubeMap.getDataType())) {
            throw new CockpitException("维度不能为时间类型");
        }
        //x轴
        ListMap xAxisMap = getMap(form.getXAxis());
        Map<String, String> xAxisDateMap = new HashMap<>();
        if (DataType.DATE.getValue().equals(xAxisMap.getDataType())) {
            xAxisDateMap = transformXAxisName(form.getField(), xAxisMap.getColumnOrl());
        }

        //x轴的刻度数值
        List<String> xAxis;
        //维度的刻度数值
        List<String> cube;

        try {
            if (DataType.DATE.getValue().equals(xAxisMap.getDataType())) {
                xAxis = new ArrayList<>(xAxisDateMap.keySet());
                Collections.sort(xAxis);
            } else {
                xAxis = listMapper.selectValueInViewColumn(view, xAxisMap.getColumnOrl());
            }
            cube = listMapper.selectValueInViewColumn(view, cubeMap.getColumnOrl());
        } catch (Exception e) {
            throw new CockpitException("无效的参数");
        }

        List<Series> series = new ArrayList<>();
        cube.forEach(c -> {
            //构造维度的刻度值名称 和 初始化维度数据数组
            series.add(Series.builder().name(c).data(new String[xAxis.size()]).build());
        });

        String where = buildWhere(form.getWhere(), view);
        for (int i = 0; i < xAxis.size(); i++) {

            List<Map<String, Object>> data;
            if (DataType.DATE.getValue().equals(xAxisMap.getDataType())) {
                data = listMapper.selectViewDataCount(view, cubeMap.getColumnOrl(), where + xAxisDateMap.get(xAxis.get(i)));
            } else {
                data = listMapper.selectViewDataCount(view, cubeMap.getColumnOrl(), where + " and " + xAxisMap.getColumnOrl() + "='" + xAxis.get(i) + "'");
            }

            //把X-data 转换为 Cube-data
            for (int j = 0; j < data.size(); j++) {

                int index = series.indexOf(Series.builder().name(data.get(j).get("NAME").toString()).build());

                Series s = series.get(index);

                s.getData()[i] = data.get(j).get("VALUE").toString();

                s.setData(s.getData());
            }
        }

        return CubeResult.builder().xAxis(xAxis).series(series).build();

    }

    @Override
    public Map<String, String> findNameMapByModelId(String modelId) {
        return listMapper.selectMap(modelId).stream()
                .map(u -> {
                    u.setColumnOrl(StringUtil.toFirstLetterUpper(u.getColumnOrl()));
                    return u;
                }).collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnCn));
    }

    @Override
    public List<String> findValueInViewColumn(String mapId) {
        List<String> list;
        ListMap listMap = getMap(mapId);
        String view = listMapper.selectViewByModelId(listMap.getModelId());
        if (StringUtils.isEmpty(view)) {
            throw new CockpitException("没有找到view");
        }
        try {
            list = listMapper.selectValueInViewColumn(view, listMap.getColumnOrl());
        } catch (Exception e) {
            throw new CockpitException("无效的参数");
        }
        return list;
    }

    private Map<String, String> transformXAxisName(Integer field, String column) {
        Map<String, String> xAxis = new HashMap<>();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat;
        if (field == null) {
            throw new CockpitException("X轴为时间类型时field必填");
        }
        switch (field) {
            case Calendar.WEEK_OF_YEAR:
                c.add(Calendar.DATE, -7);
                dateFormat = new SimpleDateFormat("MM-dd");
                while (new Date().after(c.getTime())) {
                    String key = dateFormat.format(c.getTime());
                    String start = c.get(Calendar.YEAR) + "-" + key + " 00:00:00";
                    String end = c.get(Calendar.YEAR) + "-" + key + " 23:59:59";
                    String where = " and " + column + " > to_date( '" + start + "','yyyy-mm-dd hh24:mi:ss') and " + column + " <= to_date( '" + end + "','yyyy-mm-dd hh24:mi:ss')";
                    xAxis.put(key, where);
                    c.add(Calendar.DATE, 1);
                }
                break;
            case Calendar.MONTH:
                c.add(Calendar.MONTH, -1);
                dateFormat = new SimpleDateFormat("MM-dd");
                while (new Date().after(c.getTime())) {
                    String key = dateFormat.format(c.getTime());
                    String start = c.get(Calendar.YEAR) + "-" + key + " 00:00:00";
                    String end = c.get(Calendar.YEAR) + "-" + key + " 23:59:59";
                    String where = " and " + column + " > to_date( '" + start + "','yyyy-mm-dd hh24:mi:ss') and " + column + " <= to_date( '" + end + "','yyyy-mm-dd hh24:mi:ss')";
                    xAxis.put(key, where);
                    c.add(Calendar.DATE, 1);
                }
                break;
            case Calendar.YEAR:
                c.add(Calendar.YEAR, -1);
                dateFormat = new SimpleDateFormat("yyyy-MM");
                while (new Date().after(c.getTime())) {
                    String key = dateFormat.format(c.getTime());
                    c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    String start = key + "-01" + " 00:00:00";
                    String end = key + "-" + c.getActualMaximum(Calendar.DAY_OF_MONTH) + " 23:59:59";
                    String where = " and " + column + " > to_date( '" + start + "','yyyy-mm-dd hh24:mi:ss') and " + column + " <= to_date( '" + end + "','yyyy-mm-dd hh24:mi:ss')";
                    xAxis.put(key, where);
                    c.add(Calendar.MONTH, 1);
                }
                break;
            default:
                return null;

        }
        return xAxis;
    }

    private ListMap getMap(String mapId) {
        ListMap listMap = listMapper.getMapById(mapId);
        if (listMap == null) {
            throw new CockpitException("找不到 map_id:[" + mapId + "]对应的记录");
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
                }
//                else if (entry.getValue() instanceof oracle.sql.TIMESTAMP) {
//                    // 处理 oracle.sql.TIMESTAMP 时间类型
//                    oracle.sql.TIMESTAMP timestamp = (oracle.sql.TIMESTAMP) entry.getValue();
//                    try {
//                        temp.put(nameEn, dateFormat.format(new Date(timestamp.dateValue().getTime())));
//                    } catch (SQLException e) {
//                        temp.put(nameEn, timestamp.stringValue());
//                        e.printStackTrace();
//                    }
//                }
                else {
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

    public String buildWhere(List<Where> wheres, String view) {
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
                        throw new CockpitException("时间参数[" + w.getParam() + "]格式(yyyy-MM-dd HH:mm:ss)不正确");
                    }

                    where += " and " + listMap.getColumnOrl() + " >= to_date( '" + sdf.format(startTime) + "','yyyy-mm-dd hh24:mi:ss')";
                } else {
                    int index = listMapper.selectValueInViewColumn(view, listMap.getColumnOrl()).indexOf(w.getParam());
                    if (index == -1) {
                        throw new CockpitException("参数[" + w.getParam() + "]不存在");
                    }
                    where += " and " + listMap.getColumnOrl() + " = '" + w.getParam() + "'";
                }
            }
        }
        return where;
    }

    public String buildOrder(String order) {
        if (StringUtils.isEmpty(order)) {
            return "";
        } else {
            ListMap listMap = getMap(order);
            return " ORDER BY " + listMap.getColumnOrl() + " DESC ";
        }
    }

}
